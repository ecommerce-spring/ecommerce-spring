package com.example.servicio_material.service;

import com.example.servicio_material.model.Material;
import com.example.servicio_material.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository){
        this.materialRepository = materialRepository;
    }

    public Material guardar(Material material){
        if (material.getNombre() != null) {
            material.setNombre(material.getNombre().toUpperCase());
        }
        if (material.getDetalles() != null) {
            material.setDetalles(material.getDetalles().toUpperCase());
        }
        material.setEstado(1);
        Material materialGuardado = materialRepository.save(material);
        return materialGuardado;
    }

    public List<Material> listar(){
        return materialRepository.findAll();
    }

    public Material actualizar(Long id, Material materialActualizado){
        Material materialExistente = materialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Material no encontrado con ese ID"));

        if (materialActualizado.getNombre() != null) {
            materialExistente.setNombre(materialActualizado.getNombre().toUpperCase());
        }

        return materialRepository.save(materialExistente);
    }

    public void eliminar(Long id){
        Material material = materialRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Material no encontrado con ese ID"));
        material.setEstado(2);

        materialRepository.save(material);
    }
}
