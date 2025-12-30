package com.example.servicio_material.repository;

import com.example.servicio_material.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
