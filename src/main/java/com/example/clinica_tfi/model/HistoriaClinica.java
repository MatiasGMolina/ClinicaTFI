package com.example.clinica_tfi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
@Getter @Setter
@Entity
@NoArgsConstructor
public class HistoriaClinica{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaCreacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "historia_clinica_id")  // Llave foránea en Diagnostico
    private List<Diagnostico> diagnosticos = new ArrayList<>();

    // Getters y setters
    public void agregarDiagnostico(Diagnostico diagnostico) {
        diagnosticos.add(diagnostico);
    }
    public Diagnostico getDiagnosticoPorNombre(String nombre) {
        return diagnosticos.stream()
                .filter(diagnostico -> diagnostico.getNombre().equals(nombre))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado: " + nombre));
    }
}
