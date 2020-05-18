package com.inexa.gestionstocks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionstocksApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionstocksApplication.class, args);
	}

}
