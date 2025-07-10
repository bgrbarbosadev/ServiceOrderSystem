package br.com.bgrbarbosa.sos_customers;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SosCustomersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosCustomersApplication.class, args);
	}

}
