package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.Evolucion;
import com.example.clinica_tfi.model.Medico;
import org.springframework.stereotype.Service;

@Service
public class EvolucionService {
    public Evolucion crearEvolucion(String textoLibre, String fechaHora, Medico medico) {
        Evolucion evolucion = new Evolucion();
        evolucion.setTextoLibre(textoLibre);
        evolucion.setFechaHora(fechaHora);
        evolucion.setMedico(medico); // Asignamos el médico
        return evolucion;
    }
}
