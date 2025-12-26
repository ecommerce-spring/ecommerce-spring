package com.example.marca.controller;

import com.example.marca.model.Marca;
import com.example.marca.service.MarcaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    public final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca crear(@RequestBody Marca marca){
        return marcaService.guardar(marca);
    }

    @GetMapping
    public List<Marca> obtenerMarcas(){
        return marcaService.listar();
    }

    @PutMapping("/{id}")
    public Marca actualizar(@PathVariable Long id, @RequestBody Marca marca) {
        return marcaService.actualizar(id, marca);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        marcaService.eliminar(id);
    }
}
