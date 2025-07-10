package br.com.bgrbarbosa.sos_service_order.feignclients;

import br.com.bgrbarbosa.sos_service_order.model.BusinessService;
import br.com.bgrbarbosa.sos_service_order.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(name = "sos-services", path = "/service")
public interface ServiceFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<BusinessService> searchById(@PathVariable UUID id);

}
