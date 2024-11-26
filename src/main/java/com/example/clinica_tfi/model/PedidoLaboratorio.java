package com.example.clinica_tfi.model;

import jakarta.persistence.*;

@Entity
public class PedidoLaboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String textoPedido;

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

    public String getTextoPedido() {
        return textoPedido;
    }

    public void setTextoPedido(String textoPedido) {
        this.textoPedido = textoPedido;
    }

    public Evolucion getEvolucion() {
        return evolucion;
    }

    public void setEvolucion(Evolucion evolucion) {
        this.evolucion = evolucion;
    }
}

