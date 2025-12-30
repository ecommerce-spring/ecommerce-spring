package com.example.servicio_usuario.controller;

import com.example.servicio_usuario.model.Usuario;
import com.example.servicio_usuario.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    public final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear (@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios(){
        return usuarioService.listar();
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario){
        return  usuarioService.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        usuarioService.eliminar(id);
    }
}
