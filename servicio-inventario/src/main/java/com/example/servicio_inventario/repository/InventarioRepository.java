package com.example.servicio_inventario.repository;

import com.example.servicio_inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario,Long> {
    Optional<Inventario> findBySkuCodigo(String skuCodigo);
}
