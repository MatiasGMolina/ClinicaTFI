package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.model.HistoriaClinica;
import com.example.clinica_tfi.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {
    private final PacienteService pacienteService;

    public DiagnosticoController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Agregar un diagnóstico a un paciente
    @PostMapping("/{dni}/agregar")
    public ResponseEntity<Void> agregarDiagnostico(@PathVariable String dni, @RequestBody Diagnostico diagnostico) {
        pacienteService.agregarDiagnostico(dni, diagnostico);
        return ResponseEntity.ok().build();
    }
}

