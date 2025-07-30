package com.jtorres.prueba_autos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaAutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaAutosApplication.class, args);
		System.out.println("Application: http://localhost:8080");
	}

}
