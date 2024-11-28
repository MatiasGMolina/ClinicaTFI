package com.example.clinica_tfi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Diagnostico {
    private Long id;
    private String nombre;
    private String observaciones;
    private List<Evolucion> evoluciones = new ArrayList<>();
    @JsonIgnore
    private HistoriaClinica historiaClinica;

    public void agregarEvolucion(String informe, Medico medico) {

        Evolucion evolucion = new Evolucion(informe, medico,this);

        this.evoluciones.add(evolucion);


    }

    public void agregarRecetaDigital(Long idEvolucion, List<Medicamento> medicamentos, String observaciones) {
        // Buscar la evolución por ID
        Evolucion evolucion = this.evoluciones.stream()
                .filter(e -> e.getId().equals(idEvolucion))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Evolución no encontrada"));

        // Delegar a la evolución
        evolucion.agregarRecetaDigital(medicamentos, observaciones);
    }

    public void agregarPedidoLaboratorio(Long idEvolucion, String nombreEstudio, String observaciones) {
        // Buscar la evolución por ID
        Evolucion evolucion = this.evoluciones.stream()
                .filter(e -> e.getId().equals(idEvolucion))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Evolución no encontrada"));

        // Delegar a la evolución
        evolucion.agregarPedidoLaboratorio(nombreEstudio, observaciones);
    }
}
