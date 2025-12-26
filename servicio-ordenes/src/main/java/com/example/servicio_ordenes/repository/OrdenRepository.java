package com.example.servicio_ordenes.repository;

import com.example.servicio_ordenes.model.Ordenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Ordenes, Long> {
}