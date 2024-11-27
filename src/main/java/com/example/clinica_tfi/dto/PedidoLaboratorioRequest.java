package com.example.clinica_tfi.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class PedidoLaboratorioRequest {
    private String dniPaciente;
    private Long idEvolucion;
    private Long idDiagnostico;
    private String nombreEstudio;
    private String observaciones;
}
