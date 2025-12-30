package com.example.servicio_tela.controller;

import com.example.servicio_tela.model.Tela;
import com.example.servicio_tela.service.TelaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telas")
public class TelaController {

    public final TelaService telaService;

    public TelaController(TelaService telaService) {
        this.telaService = telaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tela crear (@RequestBody Tela tela) {
        return telaService.guardar(tela);
    }

    @GetMapping
    public List<Tela> obtenerTelas(){
        return telaService.Listar();
    }

    @PutMapping("/{id}")
    public Tela actualizar(@PathVariable Long id, @RequestBody Tela tela) {
        return telaService.actualizar(id, tela);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        telaService.eliminar(id);
    }


}
