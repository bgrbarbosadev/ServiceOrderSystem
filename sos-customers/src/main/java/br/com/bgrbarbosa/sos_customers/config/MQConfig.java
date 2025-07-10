package br.com.bgrbarbosa.sos_customers.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MQConfig {

    @Value("${mq-customer-order-service}")
    private String updateCustomerServiceOrder;

    @Bean
    @Primary
    public Queue queueUpdateCustomerServiceOrder(){
        return new Queue( updateCustomerServiceOrder , true);
    }

}
