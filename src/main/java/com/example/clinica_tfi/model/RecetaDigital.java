package com.example.clinica_tfi.model;
import java.util.List;
import lombok.Getter;
import lombok.Setter;import java.util.ArrayList;

@Getter @Setter
public class RecetaDigital{
    private Long id;
    private List<Medicamento> medicamentos;
    private String observaciones;
}
