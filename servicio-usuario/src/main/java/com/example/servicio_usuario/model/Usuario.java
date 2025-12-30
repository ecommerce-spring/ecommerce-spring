package com.example.servicio_usuario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private Number rol;
    private String telefono;
    private String email;
    private String password;
    private Number estado;

}
