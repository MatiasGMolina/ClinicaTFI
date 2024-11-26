package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Diagnostico;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.clinica_tfi.service.DiagnosticoService;
@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController(DiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    @PostMapping
    public ResponseEntity<Void> crearDiagnostico(@RequestBody Diagnostico diagnostico) {
        diagnosticoService.guardarDiagnostico(diagnostico);
        return ResponseEntity.ok().build();
    }
}

