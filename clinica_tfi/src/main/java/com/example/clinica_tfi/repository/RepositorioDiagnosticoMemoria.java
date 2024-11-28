package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Diagnostico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioDiagnosticoMemoria implements RepositorioDiagnostico {

    private final List<Diagnostico> diagnosticos = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public void guardar(Diagnostico diagnostico) {
        diagnosticos.add(diagnostico);
    }

    @Override
    public List<Diagnostico> obtenerTodos() {
        return diagnosticos;
    }
}

