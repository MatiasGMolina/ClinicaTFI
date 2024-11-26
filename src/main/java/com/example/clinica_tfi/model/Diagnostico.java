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
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String observaciones;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "historia_clinica_id")  // Llave foránea en Diagnostico
    private HistoriaClinica historiaClinica;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "diagnostico_id")  // Llave foránea en Evolucion
    private List<Evolucion> evoluciones = new ArrayList<>();

    // Getters y setters
    public void agregarEvolucion(Evolucion evolucion) {
        evoluciones.add(evolucion);
    }
}
