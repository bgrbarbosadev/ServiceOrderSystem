package br.com.bgrbarbosa.sos_services;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SosServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosServicesApplication.class, args);
	}

}
