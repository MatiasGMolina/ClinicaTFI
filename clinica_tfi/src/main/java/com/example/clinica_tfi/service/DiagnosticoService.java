package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.repository.RepositorioDiagnostico;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService {

    private final RepositorioDiagnostico repositorioDiagnostico;

    public DiagnosticoService(RepositorioDiagnostico repositorioDiagnostico) {
        this.repositorioDiagnostico = repositorioDiagnostico;
    }

    public void guardarDiagnostico(Diagnostico diagnostico) {
        repositorioDiagnostico.guardar(diagnostico);
        System.out.println("Diagnóstico guardado: " + diagnostico);
    }
}