package br.com.bgrbarbosa.sos_service_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SosServiceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosServiceOrderApplication.class, args);
	}

}
