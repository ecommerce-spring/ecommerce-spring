package com.example.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        
        // IMPORTANTE: Usar setAllowedOriginPatterns en lugar de setAllowedOrigins
        // cuando se usa allowCredentials(true)
        corsConfig.setAllowedOriginPatterns(List.of("*"));
        
        // Permitir todos los métodos HTTP
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        
        // Permitir todos los headers
        corsConfig.setAllowedHeaders(List.of("*"));
        
        // Permitir credenciales (cookies, authorization headers)
        corsConfig.setAllowCredentials(true);
        
        // Tiempo de caché para la respuesta preflight (en segundos)
        corsConfig.setMaxAge(3600L);
        
        // Exponer headers personalizados que el frontend necesite leer
        corsConfig.setExposedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type",
            "X-Total-Count",
            "X-Custom-Header"
        ));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
