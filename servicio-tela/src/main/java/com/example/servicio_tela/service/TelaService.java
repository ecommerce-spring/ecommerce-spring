package com.example.servicio_tela.service;

import com.example.servicio_tela.model.Tela;
import com.example.servicio_tela.repository.TelaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TelaService {
    private final TelaRepository telaRepository;

    public TelaService(TelaRepository telaRepository) {
        this.telaRepository = telaRepository;
    }

    public Tela guardar (@RequestBody Tela tela) {
        tela.setEstado(1);
        Tela telaGuardado = telaRepository.save(tela);
        return  telaGuardado;
    }

    public List<Tela> Listar(){
        return  telaRepository.findAll();
    }

    public Tela actualizar (Long id, Tela telaActualizada){
        Tela telaExistente = telaRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Tela no encontrada con ese ID") );

        telaExistente.setNombre(telaActualizada.getNombre());

        return telaRepository.save(telaExistente);
    }

    public void eliminar (Long id){
        Tela tela = telaRepository.findById(id)
                .orElseThrow( ()-> new RuntimeException("Tela no encontrada con ese ID") );
        tela.setEstado(2);

        telaRepository.save(tela);

    }

}
