package br.com.bgrbarbosa.sos_services.controller;


import br.com.bgrbarbosa.sos_services.controller.mapper.BusinessServiceMapper;
import br.com.bgrbarbosa.sos_services.model.BusinessService;
import br.com.bgrbarbosa.sos_services.model.dto.BusinessServiceDTO;
import br.com.bgrbarbosa.sos_services.service.ServiceOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/service")
public class BusinessServiceController {

    private final ServiceOperations serviceOperations;

    private final BusinessServiceMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<BusinessServiceDTO>> searchAll(){
        List<BusinessServiceDTO> listDTO = mapper.parseToListDTO(serviceOperations.findAll());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessServiceDTO> searchById(@PathVariable("id") UUID id){
        BusinessServiceDTO result = mapper.parseToDto(serviceOperations.findById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/service")
    public ResponseEntity<BusinessServiceDTO> searchByService(@RequestParam String service){
        BusinessServiceDTO result = mapper.parseToDto(serviceOperations.findByService(service));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<BusinessServiceDTO> insert(@RequestBody @Valid BusinessServiceDTO dto) throws Exception {
        BusinessService result = serviceOperations.insert(mapper.parseToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.parseToDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
//        serviceOperations.delete(id);
//        return ResponseEntity.ok().body("Successfully deleted!!");
        try {
            String orderServiceEurekaName = "SOS-SERVICE-ORDER";
            String checkOrdersUrl = "http://" + orderServiceEurekaName + "/order/orderbyservice/" + id;
            ResponseEntity<Boolean> hasOrdersResponse = restTemplate.getForEntity(checkOrdersUrl, Boolean.class);

            if (hasOrdersResponse.getStatusCode().is2xxSuccessful() &&
                    hasOrdersResponse.getBody() != null &&
                    hasOrdersResponse.getBody() == true) {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("It is not possible to delete the client: he has linked orders.");
            } else {
                serviceOperations.delete(id);
                return ResponseEntity.ok().body("Client deleted successfully!");
            }
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("An unexpected error occurred while trying to delete the client:" + id);
        }
    }

    @PutMapping
    public ResponseEntity<BusinessServiceDTO> update(@RequestBody @Valid BusinessServiceDTO dto) throws Exception {
        BusinessService aux = mapper.parseToEntity(dto);
        return ResponseEntity.ok().body(mapper.parseToDto(serviceOperations.update(aux)));
    }



}
