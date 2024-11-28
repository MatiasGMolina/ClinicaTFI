package com.example.clinica_tfi.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoLaboratorio {
    private Long id;
    private String NombreEstudio;
    private String Observaciones;
    private Evolucion evolucion;

}

