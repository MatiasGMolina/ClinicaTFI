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
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.agregarDiagnostico(diagnostico);
    }

    public void agregarEvolucion(String dni, String nombreDiagnostico, Evolucion evolucion, String matriculaMedico) {
        // Buscar el paciente por DNI
        Paciente paciente = repositorioPaciente.buscarPacientePorDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Obtener el diagnóstico por nombre dentro de la historia clínica del paciente
        Diagnostico diagnostico = paciente.getHistoriaClinica().getDiagnosticoPorNombre(nombreDiagnostico);
        if (diagnostico == null) {
            throw new RuntimeException("Diagnóstico no encontrado en la historia clínica del paciente");
        }


        // Buscar el médico por matrícula
        Optional<Medico> optionalMedico = medicoService.buscarMedicoPorMatricula(matriculaMedico);
        Medico medico = optionalMedico.orElseThrow(() -> new RuntimeException("Médico no encontrado con matrícula: " + matriculaMedico));

        // Asignar el médico y el diagnóstico a la evolución
        evolucion.setDiagnostico(diagnostico);
        evolucion.setMedico(medico);

        // Agregar la evolución al diagnóstico
        diagnostico.agregarEvolucion(evolucion);

    }



    public List<Paciente> obtenerTodosLosPacientes() {
        return repositorioPaciente.obtenerTodos();
    }
}

