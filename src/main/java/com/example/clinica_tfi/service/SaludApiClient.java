package com.example.clinica_tfi.service;

import com.example.clinica_tfi.model.Medicamento;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class SaludApiClient {
    private final RestTemplate restTemplate;

    public SaludApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener todos los medicamentos (paginados)
    public List<Medicamento> obtenerTodosLosMedicamentos(int pagina, int limite) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://istp1service.azurewebsites.net/api/servicio-salud/medicamentos/todos")
                .queryParam("pagina", pagina)
                .queryParam("limite", limite)
                .toUriString();

        Medicamento[] medicamentos = restTemplate.getForObject(url, Medicamento[].class);
        return Arrays.asList(medicamentos);
    }

    // Buscar medicamentos por descripción
    public List<Medicamento> buscarMedicamentosPorDescripcion(String descripcion) {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://istp1service.azurewebsites.net/api/servicio-salud/medicamentos")
                .queryParam("descripcion", descripcion)
                .toUriString();

        Medicamento[] medicamentos = restTemplate.getForObject(url, Medicamento[].class);
        return Arrays.asList(medicamentos);
    }

    // Obtener medicamento por código
    public Medicamento obtenerMedicamentoPorCodigo(Long codigo) {
        String url = "https://istp1service.azurewebsites.net/api/servicio-salud/medicamentos/" + codigo;
        return restTemplate.getForObject(url, Medicamento.class);
    }
}

