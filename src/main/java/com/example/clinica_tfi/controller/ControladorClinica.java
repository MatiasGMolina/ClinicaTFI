package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.service.ClinicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")
public class ControladorClinica {

    private final ClinicaService clinicaService;

    public ControladorClinica(ClinicaService clinicaService) {
        this.clinicaService = clinicaService;
    }

    @PostMapping("/evoluciones/agregar")
    public ResponseEntity<Void> agregarEvolucion(
            @RequestParam String dniPaciente,
            @RequestParam Long idDiagnostico,
            @RequestParam String informe,
            @RequestParam String matriculaMedico) {
        // Delegar la lógica en ClienteService
        clinicaService.agregarEvolucion(dniPaciente, idDiagnostico, informe, matriculaMedico);

        return ResponseEntity.ok().build();
    }


}

