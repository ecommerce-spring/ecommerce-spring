package com.example.servicio_producto.service;

import com.example.servicio_producto.dto.InventarioEvent;
import com.example.servicio_producto.model.Producto;
import com.example.servicio_producto.repository.ProductoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter; // <--- IMPORTANTE
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final RabbitTemplate rabbitTemplate;

    // CONSTRUCTOR
    public ProductoService(ProductoRepository productoRepository, RabbitTemplate rabbitTemplate) {
        this.productoRepository = productoRepository;
        this.rabbitTemplate = rabbitTemplate;

        this.rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    public Producto guardar(Producto producto) {
        if (producto.getNombre() != null) {
            producto.setNombre(producto.getNombre().toUpperCase());
        }

        // 1. Guardar en BD
        Producto productoGuardado = productoRepository.save(producto);

        // 2. Crear Evento
        InventarioEvent evento = new InventarioEvent(
                productoGuardado.getSku(), // <--- CAMBIO AQUÍ: Usamos .getSku() en vez de .getId()
                producto.getCantidad()
        );

        // 3. Enviar a RabbitMQ
        rabbitTemplate.convertAndSend("cola-crear-inventario", evento);

        return productoGuardado;
    }

    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    public Producto actualizar (Long id , Producto productoActualizado){
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado con ese ID"));

        if (productoActualizado.getNombre() != null){
            productoActualizado.setNombre(productoActualizado.getNombre().toUpperCase());
        }
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());

        if(productoActualizado.getImagen() != null && !productoActualizado.getImagen().isEmpty()){
            productoExistente.setImagen(productoActualizado.getImagen());
        }

        productoExistente.setCantidad(productoActualizado.getCantidad());

        return productoRepository.save(productoExistente);

    }

    public void eliminar (Long id){
        Producto producto = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado con ese ID"));
        producto.setEstado(2);

        productoRepository.save(producto);

    }

}