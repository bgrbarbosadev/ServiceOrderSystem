package br.com.bgrbarbosa.sos_gateway_zull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class SosGatewayZullApplication {

	public static void main(String[] args) {
		SpringApplication.run(SosGatewayZullApplication.class, args);
	}

}
