package com.example.servicio_ordenes.service;

import com.example.servicio_ordenes.client.InventarioClient;
import com.example.servicio_ordenes.model.Ordenes;
import com.example.servicio_ordenes.repository.OrdenRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class OrdenService {

    private final OrdenRepository ordenRepository;
    private final InventarioClient inventarioClient; // Inyectamos el cliente

    public OrdenService(OrdenRepository ordenRepository, InventarioClient inventarioClient) {
        this.ordenRepository = ordenRepository;
        this.inventarioClient = inventarioClient;
    }

    public Ordenes crearOrden(Ordenes orden) {
        // 1. Preguntar a Inventario si hay stock (Llamada de red automática)
        boolean hayStock = inventarioClient.validarStock(orden.getSkuProducto());

        // 2. Tomar decisión
        if (hayStock) {
            orden.setEstado("APROBADA");
            orden.setNumeroOrden(UUID.randomUUID().toString());

            inventarioClient.reducirStock(orden.getSkuProducto(), orden.getCantidad());
        } else {
            orden.setEstado("RECHAZADA");
            orden.setNumeroOrden("N/A");
        }

        // 3. Guardar el resultado
        return ordenRepository.save(orden);
    }
}