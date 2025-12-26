package com.example.servicio_producto.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // 1. La Cola (Esto está bien)
    @Bean
    public Queue colaInventario() {
        return new Queue("cola-crear-inventario", true);
    }

    // 2. El Convertidor JSON (Esto está bien)
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 3. ¡ESTA ES LA CLAVE! Configuración manual del Template
    // Sin esto, Spring a veces ignora el convertidor de arriba.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter()); // <--- Aquí forzamos el JSON
        return rabbitTemplate;
    }
}