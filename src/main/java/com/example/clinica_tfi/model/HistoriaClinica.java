package com.example.clinica_tfi.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class HistoriaClinica{
    private Long id;
    private String fechaCreacion;
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    public void agregarDiagnostico(Diagnostico diagnostico) {
        this.diagnosticos.add(diagnostico);
        diagnostico.setHistoriaClinica(this); // Establecer relación inversa
    }

    public Diagnostico getDiagnosticoPorNombre(String nombreDiagnostico) {
        return this.diagnosticos.stream()
                .filter(d -> d.getNombre().equalsIgnoreCase(nombreDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico con el nombre '" + nombreDiagnostico + "' no encontrado"));
    }

    public void agregarEvolucion(Long idDiagnostico, String informe, Medico medico) {
        if (idDiagnostico == null) {
            throw new RuntimeException("El ID del diagnóstico no puede ser null.");
        }
        // Buscar el diagnóstico por ID
        Diagnostico diagnostico = this.diagnosticos.stream()
                .filter(d -> d.getId().equals(idDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        // Delegar al diagnóstico la lógica de agregar evolución
        diagnostico.agregarEvolucion(informe, medico);
    }
    public void agregarRecetaDigital(Long idEvolucion, Long idDiagnostico, List<Medicamento> medicamentos, String observaciones) {
        // Buscar el diagnóstico por ID
        Diagnostico diagnostico = this.diagnosticos.stream()
                .filter(d -> d.getId().equals(idDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        // Delegar al Diagnóstico
        diagnostico.agregarRecetaDigital(idEvolucion, medicamentos, observaciones);
    }

    public void agregarPedidoLaboratorio(Long idEvolucion, Long idDiagnostico, String nombreEstudio, String observaciones) {
        // Buscar el diagnóstico por ID
        Diagnostico diagnostico = this.diagnosticos.stream()
                .filter(d -> d.getId().equals(idDiagnostico))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado"));

        // Delegar al Diagnóstico
        diagnostico.agregarPedidoLaboratorio(idEvolucion, nombreEstudio, observaciones);
    }
}
