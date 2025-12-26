package com.example.servicio_producto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // --- NUEVO CAMPO ---
    @Column(unique = true) // Para que no se repitan nunca
    private String sku;
    // ------------

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private int estado;
    private String imagen;

    // --- LÓGICA DE AUTOGENERACIÓN ---
    @PrePersist
    public void generarSkuAutomatico() {
        if (this.sku == null) {
            // Genera un código aleatorio, le quita los guiones y toma los primeros 20 caracteres
            this.sku = UUID.randomUUID().toString().replace("-", "").substring(0, 20).toUpperCase();
        }
    }
}
