package com.example.clinica_tfi.service;
import com.example.clinica_tfi.model.Medico;
import com.example.clinica_tfi.repository.RepositorioMedico;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    private final RepositorioMedico repositorioMedico;

    public MedicoService(RepositorioMedico repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public Optional<Medico> buscarMedicoPorMatricula(String matricula) {
        return repositorioMedico.buscarMedicoPorMatricula(matricula);
    }
    public void registrarMedico(Medico medico) {
        repositorioMedico.guardarMedico(medico);
    }
    public List<Medico> listarTodosLosMedicos() {
        return repositorioMedico.listarTodos();
    }
}

