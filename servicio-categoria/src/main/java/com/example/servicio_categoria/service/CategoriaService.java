package com.example.servicio_categoria.service;

import com.example.servicio_categoria.model.Categoria;
import com.example.servicio_categoria.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Otros métodos del servicio...
    public Categoria guardar(Categoria categoria) {
        if (categoria.getNombre() != null){
            categoria.setNombre(categoria.getNombre().toUpperCase());
        }
        categoria.setEstado(1);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return categoriaGuardada;
    }

    public List<Categoria> listar(){
        return  categoriaRepository.findAll();
    }

    public Categoria actualizar (Long id, Categoria categoriaActualizada){
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada con ese ID"));

        if (categoriaActualizada.getNombre() != null){
            categoriaActualizada.setNombre(categoriaActualizada.getNombre().toUpperCase());
        }
        categoriaExistente.setNombre(categoriaActualizada.getNombre());

        return categoriaRepository.save(categoriaExistente);
    }

    public void eliminar (Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Categoria no encontrada con ese ID"));
        categoria.setEstado(2);

        categoriaRepository.save(categoria);
    }

}
