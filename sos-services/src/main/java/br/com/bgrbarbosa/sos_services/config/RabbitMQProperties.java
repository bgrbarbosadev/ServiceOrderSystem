package br.com.bgrbarbosa.sos_services.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class RabbitMQProperties {

    private String host;
    private int port;
    private String username;
    private String password;
}
