package com.telconet.practica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.telconet.practica.entity")
@EnableJpaRepositories("com.telconet.practica.repository")
public class TelconetPracticaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelconetPracticaApplication.class, args);
	}

}
