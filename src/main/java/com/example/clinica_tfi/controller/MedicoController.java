package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Medico;
import com.example.clinica_tfi.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    // Buscar médico por matrícula
    @GetMapping("/{matricula}")
    public ResponseEntity<Medico> buscarMedicoPorMatricula(@PathVariable String matricula) {
        Optional<Medico> medico = medicoService.buscarMedicoPorMatricula(matricula);
        return medico.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar un médico
    @PostMapping
    public ResponseEntity<Medico> registrarMedico(@RequestBody Medico medico) {
        medicoService.registrarMedico(medico);
        return ResponseEntity.ok(medico);
    }
    @GetMapping
    public ResponseEntity<List<Medico>> listarMedicos() {
        return ResponseEntity.ok(medicoService.listarTodosLosMedicos());
    }
}

