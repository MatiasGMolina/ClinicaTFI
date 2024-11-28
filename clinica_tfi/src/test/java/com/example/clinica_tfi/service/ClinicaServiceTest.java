package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.*;
import com.example.clinica_tfi.repository.RepositorioPaciente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicaServiceTest {

    @InjectMocks
    private ClinicaService clinicaService;

    @Mock
    private RepositorioPaciente repositorioPaciente;

    @Mock
    private SaludApiClient saludApiClient;

    @Test
    public void testRegistrarEvolucionTextoSimple() {
        // Configuración del paciente
        Paciente mockPaciente = new Paciente();
        mockPaciente.setDni("12345678");
        HistoriaClinica mockHistoriaClinica = new HistoriaClinica();
        Diagnostico mockDiagnostico = new Diagnostico();
        mockDiagnostico.setId(1L);
        mockHistoriaClinica.agregarDiagnostico(mockDiagnostico);
        mockPaciente.setHistoriaClinica(mockHistoriaClinica);

        // Configuración del médico
        Medico mockMedico = new Medico();
        mockMedico.setMatricula("12345");
        mockMedico.setNombre("Dr. Juan");

        // Mock del repositorio
        when(repositorioPaciente.buscarPacientePorDni("12345678"))
                .thenReturn(Optional.of(mockPaciente));

        // Acción
        clinicaService.agregarEvolucion("12345678", 1L, "Texto de evolución", mockMedico);

        // Verificaciones
        verify(repositorioPaciente, times(1)).buscarPacientePorDni("12345678");

        assertEquals(1, mockDiagnostico.getEvoluciones().size());
        Evolucion evolucion = mockDiagnostico.getEvoluciones().get(0);
        assertEquals("Texto de evolución", evolucion.getTextoLibre());
        assertEquals(mockMedico, evolucion.getMedico());
    }
    @Test
    public void testAgregarRecetaDigitalAEvolucion() {
        // Configuración del paciente
        Paciente mockPaciente = new Paciente();
        mockPaciente.setDni("12345678");
        HistoriaClinica mockHistoriaClinica = new HistoriaClinica();
        Diagnostico mockDiagnostico = new Diagnostico();
        mockDiagnostico.setId(1L);
        Evolucion mockEvolucion = new Evolucion();
        mockEvolucion.setId(1L);
        mockDiagnostico.agregarEvolucion("Informe inicial", new Medico());
        mockHistoriaClinica.agregarDiagnostico(mockDiagnostico);
        mockPaciente.setHistoriaClinica(mockHistoriaClinica);

        // Mock del repositorio
        when(repositorioPaciente.buscarPacientePorDni("12345678"))
                .thenReturn(Optional.of(mockPaciente));

        // Mock de la API para obtener medicamentos
        Medicamento medicamento1 = new Medicamento(12345L, "Paracetamol", "500 mg");
        Medicamento medicamento2 = new Medicamento(67890L, "Ibuprofeno", "400 mg");
        when(saludApiClient.obtenerMedicamentoPorCodigo(12345L)).thenReturn(medicamento1);
        when(saludApiClient.obtenerMedicamentoPorCodigo(67890L)).thenReturn(medicamento2);

        // Acción
        List<Long> codigosMedicamentos = List.of(12345L, 67890L);
        clinicaService.agregarRecetaDigitalAEvolucion(
                "12345678",
                1L,
                1L,
                codigosMedicamentos,
                "Tomar con comida"
        );

        // Verificaciones
        verify(repositorioPaciente, times(1)).buscarPacientePorDni("12345678");
        verify(saludApiClient, times(1)).obtenerMedicamentoPorCodigo(12345L);
        verify(saludApiClient, times(1)).obtenerMedicamentoPorCodigo(67890L);

        // Validar que la evolución tiene una receta digital asociada
        Evolucion evolucion = mockDiagnostico.getEvoluciones().stream()
                .filter(e -> e.getId().equals(1L))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Evolución no encontrada"));

        assertNotNull(evolucion.getRecetaDigital());
        assertEquals("Tomar con comida", evolucion.getRecetaDigital().getObservaciones());
        assertEquals(2, evolucion.getRecetaDigital().getMedicamentos().size());
        assertEquals("Paracetamol", evolucion.getRecetaDigital().getMedicamentos().get(0).getDescripcion());
        assertEquals("Ibuprofeno", evolucion.getRecetaDigital().getMedicamentos().get(1).getDescripcion());
    }
    @Test
    public void agregarRecetaDigitalAEvolucion_deberiaTirarException_cuandoSeMandanMasDeDosMedicamentos() {
        List<Long> codigosMedicamentos = List.of(12345L, 67890L, 11111L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            clinicaService.agregarRecetaDigitalAEvolucion("12345678", 1L, 1L, codigosMedicamentos, "Observaciones");
        });

        assertEquals("Solo se pueden agregar hasta dos medicamentos.", exception.getMessage());
    }
    @Test
    public void agregarEvolucion_deberiaTirarException_cuandoPacienteNoEncontrado() {
        when(repositorioPaciente.buscarPacientePorDni("99999999")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clinicaService.agregarEvolucion("99999999", 1L, "Informe", new Medico());
        });

        assertEquals("Paciente no encontrado", exception.getMessage());
    }

}




