package com.example.servicio_marca.service;

import com.example.servicio_marca.model.Marca;
import com.example.servicio_marca.repository.MarcaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;

    }

    public Marca guardar(Marca marca) {
        marca.setEstado(1);
        Marca marcaGuardado = marcaRepository.save(marca);
        return marcaGuardado;
    }

    public List<Marca> listar(){
        return  marcaRepository.findAll();
    }

    public Marca actualizar (Long id, Marca marcaActualizada){
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Marca no encontrada con ese ID"));

        marcaExistente.setNombre(marcaActualizada.getNombre());

        return marcaRepository.save(marcaExistente);
    }

    public void eliminar (Long id){
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Marca no encontrada con ese ID"));
        marca.setEstado(2);

        marcaRepository.save(marca);
    }
}
