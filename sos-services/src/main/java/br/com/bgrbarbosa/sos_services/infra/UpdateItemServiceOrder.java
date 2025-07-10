package br.com.bgrbarbosa.sos_services.infra;


import br.com.bgrbarbosa.sos_services.model.BusinessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UpdateItemServiceOrder {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueItemCustomer;

    public void updateService(BusinessService entity) throws JsonProcessingException {
        var json = convertIntoJson(entity);
        rabbitTemplate.convertAndSend(queueItemCustomer.getName(), json);
    }

    private String convertIntoJson(BusinessService entity) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(entity);
        return json;
    }

    public void deleteCustomer(UUID uuid) throws JsonProcessingException {
        var json = convertIntoJson(uuid);
        rabbitTemplate.convertAndSend(queueItemCustomer.getName(), json);
    }

    private String convertIntoJson(UUID uuid) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(uuid);
        return json;
    }
}
