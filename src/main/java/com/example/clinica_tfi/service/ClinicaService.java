package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.model.Medico;
import com.example.clinica_tfi.model.Paciente;
import com.example.clinica_tfi.repository.RepositorioPaciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaService {

    private final RepositorioPaciente repositorioPaciente;
    private final PacienteService pacienteService;
    private final MedicoService medicoService;

    public ClinicaService(RepositorioPaciente repositorioPaciente,PacienteService pacienteService, MedicoService medicoService) {
        this.repositorioPaciente = repositorioPaciente;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
    }

    public void agregarEvolucion(String dniPaciente, Long idDiagnostico, String informe, String matriculaMedico) {
        // Buscar al paciente directamente desde el repositorio
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dniPaciente)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // 1. Buscar al médico por matrícula
        Medico medico = medicoService.buscarMedicoPorMatricula(matriculaMedico)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        // Delegar la lógica restante a PacienteService
        pacienteService.agregarEvolucion(paciente, idDiagnostico, informe, medico);
    }

    

}


