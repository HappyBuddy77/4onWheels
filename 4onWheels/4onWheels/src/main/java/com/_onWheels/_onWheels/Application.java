package com._onWheels._onWheels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
// @ComponentScan(basePackages = {"com._onWheels._onWheels"})
public class Application {

	public static void main(String[] args) {
		// Make sure to add variables to .env at the root of the project 
		Dotenv dotenv = Dotenv.load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(Application.class, args);
	}

}
