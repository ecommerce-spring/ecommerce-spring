package com.example.registro_servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistroServicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroServicioApplication.class, args);
	}

}
