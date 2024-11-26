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




}
