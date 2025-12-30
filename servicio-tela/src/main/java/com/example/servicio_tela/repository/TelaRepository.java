package com.example.servicio_tela.repository;

import com.example.servicio_tela.model.Tela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelaRepository extends JpaRepository<Tela, Long > {
}
