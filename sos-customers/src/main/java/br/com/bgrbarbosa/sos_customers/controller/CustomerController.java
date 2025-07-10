package br.com.bgrbarbosa.sos_customers.controller;


import br.com.bgrbarbosa.sos_customers.controller.mapper.CustomerMapper;
import br.com.bgrbarbosa.sos_customers.model.Customer;
import br.com.bgrbarbosa.sos_customers.model.dto.CustomerDTO;
import br.com.bgrbarbosa.sos_customers.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    private final CustomerMapper mapper;

    private final MessageSource messageSource;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> searchAll(){
        List<CustomerDTO> listDTO = mapper.parseToListDTO(service.findAll());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> searchById(@PathVariable("id") UUID id){
        CustomerDTO result = mapper.parseToDto(service.findById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> insert(@RequestBody @Valid CustomerDTO dto) throws Exception {
        Customer result = mapper.parseToEntity(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.parseToDto(service.insert(result)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        try {
            String orderServiceEurekaName = "SOS-SERVICE-ORDER";
            String checkOrdersUrl = "http://" + orderServiceEurekaName + "/order/orderbycustomer/" + id;
            ResponseEntity<Boolean> hasOrdersResponse = restTemplate.getForEntity(checkOrdersUrl, Boolean.class);

            if (hasOrdersResponse.getStatusCode().is2xxSuccessful() &&
                    hasOrdersResponse.getBody() != null &&
                    hasOrdersResponse.getBody() == true) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("It is not possible to delete the client: he has linked orders.");
            } else {
                service.delete(id);
                return ResponseEntity.ok().body("Client deleted successfully!");
            }
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("An unexpected error occurred while trying to delete the client:" + id);
        }
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody @Valid CustomerDTO dto) throws Exception {
        Customer aux = mapper.parseToEntity(dto);
        return ResponseEntity.ok().body(mapper.parseToDto(service.update(aux)));
    }

}
