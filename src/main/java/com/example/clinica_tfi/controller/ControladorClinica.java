package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.dto.PedidoLaboratorioRequest;
import com.example.clinica_tfi.dto.RecetaDigitalRequest;
import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.model.Medico;
import com.example.clinica_tfi.model.RecetaDigital;
import com.example.clinica_tfi.service.ClinicaService;
import com.example.clinica_tfi.service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinica")
public class ControladorClinica {

    private final ClinicaService clinicaService;
    private final MedicoService medicoService;


    public ControladorClinica(ClinicaService clinicaService, MedicoService medicoService) {
        this.medicoService = medicoService;
        this.clinicaService = clinicaService;
    }

    @PostMapping("/evoluciones/agregar")
    public ResponseEntity<Void> agregarEvolucion(
            @RequestParam String dniPaciente,
            @RequestParam Long idDiagnostico,
            @RequestParam String informe,
            @RequestParam String matriculaMedico) {
        // 1. Buscar al médico por matrícula
        Medico medico = medicoService.buscarMedicoPorMatricula(matriculaMedico)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        // Delegar la lógica en ClienteService
        clinicaService.agregarEvolucion(dniPaciente, idDiagnostico, informe, medico);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/recetas-digitales/agregar-a-evolucion")
    public ResponseEntity<Void> agregarRecetaDigitalAEvolucion(
            @RequestBody RecetaDigitalRequest request) {

        clinicaService.agregarRecetaDigitalAEvolucion(
                request.getDniPaciente(),
                request.getIdEvolucion(),
                request.getIdDiagnostico(),
                request.getCodigosMedicamentos(),
                request.getObservaciones()
        );

        return ResponseEntity.ok().build();
    }
    @PostMapping("/pedido-laboratorio/agregar-a-evolucion")
    public ResponseEntity<Void> agregarPedidoLabAEvolucion(
            @RequestBody PedidoLaboratorioRequest request) {

        clinicaService.agregarPedidoLaboratorio(
                request.getDniPaciente(),
                request.getIdEvolucion(),
                request.getIdDiagnostico(),
                request.getNombreEstudio(),
                request.getObservaciones()
        );

        return ResponseEntity.ok().build();
    }




}

