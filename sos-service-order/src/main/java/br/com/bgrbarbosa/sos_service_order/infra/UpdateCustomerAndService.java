package br.com.bgrbarbosa.sos_service_order.infra;

import br.com.bgrbarbosa.sos_service_order.model.Customer;
import br.com.bgrbarbosa.sos_service_order.repository.CustomerRepository;
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
public class UpdateCustomerAndService {

    private final CustomerRepository customerRepository;

    @RabbitListener(queues = "${mq-customer-order-service}")
    public void updateCustomer(@Payload String payload){
        try {
            var mapper = new ObjectMapper();

            Customer data = mapper.readValue(payload, Customer.class);
            customerRepository.save(data);

        }catch (Exception e){
            log.error("Error receiving data from {}", e.getMessage());
        }
    }

    @RabbitListener(queues = "${mq-customer-order-service}")
    public void deleteCustomer(@Payload String idString){
        String cleanIdString = idString.replace("\"", "");
        UUID id = UUID.fromString(cleanIdString);
        try {
            log.info("Attempting to delete customer with UUID: {}", id);
            customerRepository.deleteById(id);
            log.info("Customer with UUID {} deleted successfully.", id);
        }catch (Exception e){
            log.error("Error deleting customer with UUID {}: {}", id, e.getMessage());
        }
    }
}
