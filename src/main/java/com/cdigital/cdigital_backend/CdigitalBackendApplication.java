package com.cdigital.cdigital_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CdigitalBackendApplication {

	public static void main(String[] args) {
		//Dotenv dotenv = Dotenv.load();
		//System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD") null)
		SpringApplication.run(CdigitalBackendApplication.class, args);
		System.out.println("Hello, welcome back");
	}

}
