package com.example.servicio_ordenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration; // <--- OJO CON ESTE IMPORT
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {
		SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class // <--- AGREGA ESTA LÍNEA
})
@EnableFeignClients
public class ServicioOrdenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioOrdenesApplication.class, args);
	}
}