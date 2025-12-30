package com.example.servicio_material.controller;

import com.example.servicio_material.model.Material;
import com.example.servicio_material.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {
    public final MaterialService materialService;

    public MaterialController(MaterialService materialService){
        this.materialService = materialService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Material crear(@RequestBody Material material){
        return materialService.guardar(material);
    }

    @GetMapping
    public List<Material> obtenerMaterial(){
        return materialService.listar();
    }

    @PutMapping("/{id}")
    public Material actualizar(@PathVariable Long id, @RequestBody Material material){
        return materialService.actualizar(id, material);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        materialService.eliminar(id);
    }

}
