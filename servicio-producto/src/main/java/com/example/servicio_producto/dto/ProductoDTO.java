package com.example.servicio_producto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDTO {

    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;
    private int estado;
    private String imagen;
}
