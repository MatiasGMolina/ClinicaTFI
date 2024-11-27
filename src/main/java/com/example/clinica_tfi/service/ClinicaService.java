package com.example.clinica_tfi.service;
import com.example.clinica_tfi.model.*;
import com.example.clinica_tfi.repository.RepositorioPaciente;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicaService {
    private final RepositorioPaciente repositorioPaciente;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;
    private final SaludApiClient saludApiClient;

    public ClinicaService(RepositorioPaciente repositorioPaciente,PacienteService pacienteService,
                          MedicoService medicoService, SaludApiClient saludApiClient) {
        this.saludApiClient = saludApiClient;
        this.repositorioPaciente = repositorioPaciente;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    public void agregarEvolucion(String dniPaciente, Long idDiagnostico, String informe, Medico medico) {

        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.validarHistoriaClinica();
        paciente.agregarEvolucion(idDiagnostico, informe, medico);
    }
    public void agregarRecetaDigitalAEvolucion(String dniPaciente, Long idEvolucion, Long idDiagnostico, List<Long> codigosMedicamentos, String observaciones) {

        // Validar que solo se puedan agregar hasta dos medicamentos
        if (codigosMedicamentos.size() > 2) {
            throw new IllegalArgumentException("Solo se pueden agregar hasta dos medicamentos.");
        }
        // Validar se agrego un medicamento como minimo
        if (codigosMedicamentos.isEmpty()) {
            throw new IllegalArgumentException("Debe agregar al menos un medicamento.");
        }
        // Buscar al paciente
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        // Obtener medicamentos desde la API
        List<Medicamento> medicamentos = codigosMedicamentos.stream()
                .map(codigo -> saludApiClient.obtenerMedicamentoPorCodigo(codigo))
                .collect(Collectors.toList());
        // Delegar la creación de la receta digital al paciente
        paciente.agregarRecetaDigital(idEvolucion, idDiagnostico, medicamentos, observaciones);
    }

    public void agregarPedidoLaboratorio(String dniPaciente, Long idEvolucion,Long idDiagnositico, String nombreEstudio, String observaciones) {
        // Buscar al paciente
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Delegar la creación del pedido de laboratorio al paciente
        paciente.agregarPedidoLaboratorio(idEvolucion,idDiagnositico, nombreEstudio, observaciones);
    }
}


