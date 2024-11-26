package com.example.clinica_tfi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@Entity
@NoArgsConstructor
public class Evolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaHora;
    private String textoLibre;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "diagnostico_id")  // Llave foránea en Evolucion
    private Diagnostico diagnostico;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evolucion_id")
    private List<RecetaDigital> recetasDigitales = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evolucion_id")
    private List<PedidoLaboratorio> pedidosLaboratorio = new ArrayList<>();

    // Métodos para agregar
    public void agregarReceta(RecetaDigital receta) {
        this.recetasDigitales.add(receta);
    }

    public void agregarPedidoLaboratorio(PedidoLaboratorio pedido) {
        this.pedidosLaboratorio.add(pedido);
    }

    // Getters y setters
}
