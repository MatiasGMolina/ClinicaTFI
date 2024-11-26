package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Evolucion;
import com.example.clinica_tfi.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evoluciones")
public class EvolucionController {
    private final PacienteService pacienteService;

    public EvolucionController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/{dni}/{diagnosticoNombre}/agregar")
    public ResponseEntity<Void> registrarEvolucion(
            @PathVariable String dni,
            @PathVariable String diagnosticoNombre,
            @RequestBody Evolucion evolucion,
            @RequestParam String matriculaMedico) {
        pacienteService.agregarEvolucion(dni, diagnosticoNombre, evolucion, matriculaMedico);
        return ResponseEntity.ok().build();
    }
}

