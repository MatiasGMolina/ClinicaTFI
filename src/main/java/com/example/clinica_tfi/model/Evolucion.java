package com.example.clinica_tfi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Evolucion {
    private static long idCounter = 1L; // Contador estático
    private Long id;
    private String fechaHora;
    private String textoLibre;
    private Medico medico;
    private RecetaDigital recetaDigital;
    private PedidoLaboratorio pedidoLaboratorio;
    @JsonIgnore
    private Diagnostico diagnostico;

    public Evolucion(String textoLibre, Medico medico, Diagnostico diagnostico) {
        this.textoLibre = textoLibre;
        this.medico = medico;
        this.diagnostico = diagnostico;
        this.id = idCounter++;
    }

    public void agregarRecetaDigital(List<Medicamento> medicamentos, String observaciones) {

        if (this.recetaDigital != null) {
            throw new RuntimeException("Esta evolución ya tiene una receta digital asociada.");
        }

        // Crear y asociar la receta digital
        RecetaDigital recetaDigital = new RecetaDigital();
        recetaDigital.setMedicamentos(medicamentos);
        recetaDigital.setObservaciones(observaciones);

        this.recetaDigital = recetaDigital;
    }

    public void agregarPedidoLaboratorio(String nombreEstudio, String observaciones) {
        if (this.pedidoLaboratorio != null) {
            throw new RuntimeException("Esta evolución ya tiene un pedido de laboratorio asociado.");
        }

        // Crear y asociar el pedido de laboratorio
        PedidoLaboratorio pedidoLaboratorio = new PedidoLaboratorio();
        pedidoLaboratorio.setNombreEstudio(nombreEstudio);
        pedidoLaboratorio.setObservaciones(observaciones);

        this.pedidoLaboratorio = pedidoLaboratorio;
    }
}
