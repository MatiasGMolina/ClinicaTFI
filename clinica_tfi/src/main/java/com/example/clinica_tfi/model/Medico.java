package com.example.clinica_tfi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Medico {
    private Long id;
    private String nombre;
    private String matricula;
    private String especialidad;

    @JsonIgnore
    private List<Evolucion> evoluciones = new ArrayList<>();

    @Override
    public String toString() {
        return "Medico{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
