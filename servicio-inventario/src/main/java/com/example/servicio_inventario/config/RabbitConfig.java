package com.example.servicio_inventario.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 1. Definimos la cola (Solo es estrictamente necesario en el Consumidor,
    // pero ponerlo en ambos asegura que la cola exista sí o sí).
    @Bean
    public Queue colaInventario() {
        return new Queue("cola-crear-inventario", true); // true = durable (no se borra si reinicias)
    }

    // 2. Convertidor para poder mandar JSON en lugar de bytes raros
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}