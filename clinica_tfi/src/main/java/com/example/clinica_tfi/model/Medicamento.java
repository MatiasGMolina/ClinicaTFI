package com.example.clinica_tfi.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Medicamento {
    private Long codigo;
    private String descripcion;
    private String formato;

    public Medicamento() {
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", formato='" + formato + '\'' +
                '}';
    }

    public Medicamento(Long codigo,String descripcion,String formato){
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.formato = formato;
    }
}
