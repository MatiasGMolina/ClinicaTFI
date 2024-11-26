package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Diagnostico;

import java.util.List;

public interface RepositorioDiagnostico {
    void guardar(Diagnostico diagnostico);
    List<Diagnostico> obtenerTodos();
}

