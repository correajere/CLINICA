package com.microservice.pacientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// esta anotación no es necesaria ya que eureka lo registra como cliente
// solamente para tenerla explícita
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicePacientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePacientesApplication.class, args);
	}

}
