package com.example.servicio_ordenes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ordenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroOrden; // Un código único (UUID)
    private String skuProducto; // El producto que quiere comprar
    private int cantidad;
    private String estado; // "APROBADA" o "RECHAZADA"
}