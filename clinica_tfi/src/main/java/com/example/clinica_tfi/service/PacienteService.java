package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.*;
import com.example.clinica_tfi.repository.RepositorioPaciente;
import org.springframework.stereotype.Service;
import com.example.clinica_tfi.service.MedicoService;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final RepositorioPaciente repositorioPaciente;
    private final MedicoService medicoService;

    public PacienteService(RepositorioPaciente repositorioPaciente, MedicoService medicoService) {
        this.repositorioPaciente = repositorioPaciente;
        this.medicoService = medicoService; // Aquí se inyecta
    }

    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return repositorioPaciente.buscarPacientePorDni(dni);
    }

    public void guardarPaciente(Paciente paciente) {
        repositorioPaciente.guardarPaciente(paciente);
    }

    public void agregarHistoriaClinica(String dni, HistoriaClinica historiaClinica) {
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.agregarHistoriaClinica(historiaClinica);
    }

    public void agregarDiagnostico(String dni, Diagnostico diagnostico) {
        if (diagnostico.getId() == null) {
            throw new RuntimeException("El diagnóstico no tiene un ID asignado.");
        }

        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.agregarDiagnostico(diagnostico);
    }

    public List<Diagnostico> obtenerDiagnosticosDePaciente(String dni) {
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        if (paciente.getHistoriaClinica() == null) {
            throw new RuntimeException("El paciente no tiene una historia clínica asociada.");
        }

        return paciente.getHistoriaClinica().getDiagnosticos();
    }

    public List<Paciente> obtenerTodosLosPacientes() {
        return repositorioPaciente.obtenerTodos();
    }
}

