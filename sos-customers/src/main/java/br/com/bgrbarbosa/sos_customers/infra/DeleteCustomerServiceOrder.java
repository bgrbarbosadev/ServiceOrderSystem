package br.com.bgrbarbosa.sos_customers.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class DeleteCustomerServiceOrder {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueDeleteCustomer;

    public void deleteCustomer(UUID uuid) throws JsonProcessingException {
        var json = convertIntoJson(uuid);
        rabbitTemplate.convertAndSend(queueDeleteCustomer.getName(), json);
    }

    private String convertIntoJson(UUID uuid) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(uuid);
        return json;
    }
}
