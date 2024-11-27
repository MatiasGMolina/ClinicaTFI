package com.example.clinica_tfi.dto;

import java.util.List;

public class RecetaDigitalRequest {
    private String dniPaciente;
    private Long idEvolucion;
    private Long idDiagnostico;
    private List<Long> codigosMedicamentos;
    private String observaciones;

    // Getters y setters
    public String getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(String dniPaciente) {
        this.dniPaciente = dniPaciente;
    }

    public Long getIdEvolucion() {
        return idEvolucion;
    }

    public void setIdEvolucion(Long idEvolucion) {
        this.idEvolucion = idEvolucion;
    }

    public Long getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Long idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public List<Long> getCodigosMedicamentos() {
        return codigosMedicamentos;
    }

    public void setCodigosMedicamentos(List<Long> codigosMedicamentos) {
        this.codigosMedicamentos = codigosMedicamentos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}


