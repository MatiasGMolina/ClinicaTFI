package com.example.clinica_tfi.repository;

import com.example.clinica_tfi.model.Medico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RepositorioMedicoMemoria implements RepositorioMedico {
    private final List<Medico> medicos = new ArrayList<>();

    @Override
    public Optional<Medico> buscarMedicoPorMatricula(String matricula) {
        // Limpiar la matrícula buscada
        String matriculaLimpia = matricula.trim().replaceAll("[\\r\\n]", "");

        // Buscar el médico en la lista
        return medicos.stream()
                .filter(medico -> medico.getMatricula().trim().replaceAll("[\\r\\n]", "").equalsIgnoreCase(matriculaLimpia))
                .findFirst();
    }

    @Override
    public void guardarMedico(Medico medico) {
        medicos.add(medico);
    }

    @Override
    public List<Medico> listarTodos() {
        return medicos;
    }
}

