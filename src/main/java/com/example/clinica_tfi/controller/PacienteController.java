package com.example.clinica_tfi.controller;

import com.example.clinica_tfi.model.Diagnostico;
import com.example.clinica_tfi.model.HistoriaClinica;
import com.example.clinica_tfi.model.Paciente;
import com.example.clinica_tfi.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // Buscar paciente por DNI
    @GetMapping("/{dni}")
    public ResponseEntity<Paciente> buscarPacientePorDni(@PathVariable String dni) {
        Optional<Paciente> paciente = pacienteService.buscarPacientePorDni(dni);
        return paciente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Registrar un paciente
    @PostMapping
    public ResponseEntity<Paciente> guardarPaciente(@RequestBody Paciente paciente) {
        pacienteService.guardarPaciente(paciente);
        return ResponseEntity.ok(paciente);
    }

    // Agregar una historia clínica a un paciente
    @PostMapping("/{dni}/historia-clinica")
    public ResponseEntity<Void> agregarHistoriaClinica(@PathVariable String dni, @RequestBody HistoriaClinica historiaClinica) {
        pacienteService.agregarHistoriaClinica(dni, historiaClinica);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{dni}/diagnosticos")
    public ResponseEntity<Void> asociarDiagnostico(
            @PathVariable String dni,
            @RequestBody Diagnostico diagnostico) {
        pacienteService.agregarDiagnostico(dni, diagnostico);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{dni}/diagnosticos")
    public List<Diagnostico> obtenerDiagnosticos(@PathVariable String dni) {
        return pacienteService.obtenerDiagnosticosDePaciente(dni);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = pacienteService.obtenerTodosLosPacientes();
        System.out.println("Número de pacientes en memoria: " + pacientes.size());
        return ResponseEntity.ok(pacientes);
    }
}
