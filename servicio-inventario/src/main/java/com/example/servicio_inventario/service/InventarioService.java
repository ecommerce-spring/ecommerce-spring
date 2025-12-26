package com.example.servicio_inventario.service;

import com.example.servicio_inventario.model.Inventario;
import com.example.servicio_inventario.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Guardar o actualizar stock
    public Inventario guardarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    // Listar todo
    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll();
    }

    // Lógica de negocio: Verificar si hay stock de un SKU
    public boolean hayStock(String sku) {
        Optional<Inventario> inventario = inventarioRepository.findBySkuCodigo(sku);
        // Si existe el producto Y la cantidad es mayor a 0, retorna true
        return inventario.isPresent() && inventario.get().getCantidad() > 0;
    }

    public void reducirStock(String sku, int cantidad) {
        Inventario inventario = inventarioRepository.findBySkuCodigo(sku)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado"));

        if  (inventario.getCantidad() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setCantidad(inventario.getCantidad() - cantidad);
        inventarioRepository.save(inventario);
    }
}