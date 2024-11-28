package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class RepositorioPacienteMemoria implements RepositorioPaciente {
    private final List<Paciente> pacientes = new ArrayList<>();

    @Override
    public Optional<Paciente> buscarPacientePorDni(String dni) {
        return pacientes.stream()
                .filter(paciente -> paciente.getDni().equals(dni)).findFirst();
    }

    @Override
    public void guardarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    @Override
    public List<Paciente> obtenerTodos() {
        return pacientes;
    }
}

