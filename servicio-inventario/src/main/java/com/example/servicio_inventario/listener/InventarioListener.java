package com.example.servicio_inventario.listener;

import com.example.servicio_inventario.dto.InventarioEvent;
import com.example.servicio_inventario.model.Inventario;
import com.example.servicio_inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventarioListener {

    private final InventarioService inventarioService;

    // Esta anotación es la oreja que escucha la cola
    @RabbitListener(queues = "cola-crear-inventario")
    public void recibirMensaje(InventarioEvent evento) {

        System.out.println("📬 Mensaje Recibido: Crear stock para " + evento.getSku());

        // Transformamos el Evento en una Entidad Inventario
        Inventario nuevoInventario = new Inventario();
        nuevoInventario.setSkuCodigo(evento.getSku());
        nuevoInventario.setCantidad(evento.getCantidad());

        // Llamamos a tu servicio original
        inventarioService.guardarInventario(nuevoInventario);
    }
}