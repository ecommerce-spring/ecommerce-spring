package com.example.servicio_ordenes.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// name = El nombre EXACTO que aparece en Eureka del servicio de inventario
@FeignClient(name = "servicio-inventario")
public interface InventarioClient {

    // Esta ruta debe coincidir con el Controller de Inventario
    @GetMapping("/api/inventario/{sku}")
    boolean validarStock(@PathVariable("sku") String sku);

    // NUEVO MÉTODO PARA RESTAR
    @PutMapping("/api/inventario/reducir/{sku}")
    void reducirStock(@PathVariable("sku") String sku, @RequestBody Integer cantidad);
}