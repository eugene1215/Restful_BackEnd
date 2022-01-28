package com.example.microservice.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class microserviceApplication {

	public static void main(String[] args) {
//		 System.out.println("version: " + SpringVersion.getVersion());

		
		SpringApplication.run(microserviceApplication.class, args);
	}

	
}
