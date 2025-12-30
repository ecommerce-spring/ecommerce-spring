package com.example.servicio_usuario.service;

import com.example.servicio_usuario.model.Usuario;
import com.example.servicio_usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UsuarioService {
    public final UsuarioRepository usuarioRepository;

    public UsuarioService (UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardar (@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());

        return usuarioRepository.save(usuarioExistente);
    }

    public void eliminar(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Usuario no encontrado"));

        usuario.setEstado(2);

        usuarioRepository.save(usuario);

    }

}
