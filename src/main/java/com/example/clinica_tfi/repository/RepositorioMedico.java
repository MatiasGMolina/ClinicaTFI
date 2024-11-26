package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Medico;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioMedico {
    Optional<Medico> buscarMedicoPorMatricula(String matricula);
    void guardarMedico(Medico medico);
    List<Medico> listarTodos();
}
