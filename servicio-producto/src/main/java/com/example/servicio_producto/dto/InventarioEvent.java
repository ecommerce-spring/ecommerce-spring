package com.example.servicio_producto.dto; // Ajusta el paquete según proyecto

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventarioEvent {
    private String sku;
    private Integer cantidad;
}