package br.com.bgrbarbosa.sos_service_order.infra;

import br.com.bgrbarbosa.sos_service_order.model.BusinessService;
import br.com.bgrbarbosa.sos_service_order.model.Customer;
import br.com.bgrbarbosa.sos_service_order.repository.CustomerRepository;
import br.com.bgrbarbosa.sos_service_order.repository.ServiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UpdateService {

    private final ServiceRepository repository;

    @RabbitListener(queues = "${mq-item-service-order-service}")
    public void updateService(@Payload String payload){
        try {
            var mapper = new ObjectMapper();

            BusinessService data = mapper.readValue(payload, BusinessService.class);
            repository.save(data);

        }catch (Exception e){
            log.error("Error receiving data from {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "${mq-item-service-order-service}")
    public void deleteCustomer(@Payload String idString){
        String cleanIdString = idString.replace("\"", "");
        UUID id = UUID.fromString(cleanIdString);
        try {
            log.info("Attempting to delete customer with UUID: {}", id);
            repository.deleteById(id);
            log.info("Customer with UUID {} deleted successfully.", id);
        }catch (Exception e){
            log.error("Error deleting customer with UUID {}: {}", id, e.getMessage());
        }
    }
}
