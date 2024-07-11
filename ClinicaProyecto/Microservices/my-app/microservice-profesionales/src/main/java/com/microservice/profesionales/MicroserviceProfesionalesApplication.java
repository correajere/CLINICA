package com.microservice.profesionales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProfesionalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceProfesionalesApplication.class, args);
	}

}
