package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.model.Evolucion;
import com.example.clinica_tfi.model.HistoriaClinica;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaService {
    public void agregarDiagnostico(HistoriaClinica historiaClinica, Diagnostico diagnostico) {
        historiaClinica.agregarDiagnostico(diagnostico);
    }
}

