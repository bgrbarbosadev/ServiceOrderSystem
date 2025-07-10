package br.com.bgrbarbosa.sos_services.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq-item-service-order-service}")
    private String updateItemServiceOrder;

    @Bean
    public Queue queueUpdateItemServiceOrder(){
        return new Queue( updateItemServiceOrder , true);
    }
}
