package com.example.servicio_ordenes.controller;

import com.example.servicio_ordenes.model.Ordenes;
import com.example.servicio_ordenes.service.OrdenService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ordenes crear(@RequestBody Ordenes orden) {
        return ordenService.crearOrden(orden);
    }
}