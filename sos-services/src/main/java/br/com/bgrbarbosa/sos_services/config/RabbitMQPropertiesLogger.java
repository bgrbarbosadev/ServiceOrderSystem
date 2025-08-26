package br.com.bgrbarbosa.sos_services.config;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class RabbitMQPropertiesLogger {

    private final RabbitMQProperties rabbitMQProperties;

    @Bean
    public CommandLineRunner logRabbitMQProperties() {
        return args -> {
            log.info("--- RabbitMQ Configuration ---");
            log.info("Host: {}", rabbitMQProperties.getHost());
            log.info("Port: {}", rabbitMQProperties.getPort());
            log.info("Username: {}", rabbitMQProperties.getUsername());
            log.info("Password: {}", rabbitMQProperties.getPassword());
            log.info("--- End of Configuration ---");
        };
    }
}
