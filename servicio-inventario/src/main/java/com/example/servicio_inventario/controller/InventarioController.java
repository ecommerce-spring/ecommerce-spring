package com.example.servicio_inventario.controller;

import com.example.servicio_inventario.model.Inventario;
import com.example.servicio_inventario.service.InventarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario") // Usamos /api por convención
public class InventarioController {

    private final InventarioService inventarioService;

    // Inyectamos el Servicio, NO el Repositorio
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventario crear(@RequestBody Inventario inventario) {
        return inventarioService.guardarInventario(inventario);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Inventario> listar() {
        return inventarioService.listarInventario();
    }

    // Endpoint clave: Otro microservicio llamará a este para saber si puede vender
    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public boolean validarStock(@PathVariable String sku) {
        return inventarioService.hayStock(sku);
    }

    //    Metodo: Restar stock
    @PutMapping("/reducir/{sku}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204: Significa "Lo hice, pero no devuelvo nada"
    public void reducirStock(@PathVariable String sku, @RequestBody Integer cantidad) {
        inventarioService.reducirStock(sku, cantidad);
    }
}