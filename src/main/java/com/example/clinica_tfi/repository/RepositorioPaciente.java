package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioPaciente {
    Optional<Paciente> buscarPacientePorDni(String dni);
    void guardarPaciente(Paciente paciente);
    List<Paciente> obtenerTodos();
}

