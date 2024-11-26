package com.example.clinica_tfi.model;

import jakarta.persistence.*;

@Entity
public class RecetaDigital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String codigoBarras;
    private String firmaDigital;
    private String medicamento;

    @ManyToOne
    @JoinColumn(name = "evolucion_id") // Llave foránea
    private Evolucion evolucion;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getFirmaDigital() {
        return firmaDigital;
    }

    public void setFirmaDigital(String firmaDigital) {
        this.firmaDigital = firmaDigital;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public Evolucion getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(Evolucion evolucion) {
        this.evolucion = evolucion;
    }
}
