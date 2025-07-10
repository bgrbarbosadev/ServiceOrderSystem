package br.com.bgrbarbosa.sos_service_order.controller;



import br.com.bgrbarbosa.sos_service_order.model.Order;
import br.com.bgrbarbosa.sos_service_order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> searchAll(){
        List<Order> listDTO = service.findAll();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> searchById(@PathVariable("id") UUID id){
        Order result = service.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/orderbycustomer/{id}")
    public ResponseEntity<Boolean> searchOrderByCustomer(@PathVariable("id") UUID id){
        return ResponseEntity.ok(service.findOrderByIdCustomer(id));
    }

    @GetMapping("/orderbyservice/{id}")
    public ResponseEntity<Boolean> searchOrderByService(@PathVariable("id") UUID id){
        return ResponseEntity.ok(service.findOrderByIdService(id));
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponseEntity.ok().body("Successfully deleted!!");
    }

    @PutMapping
    public ResponseEntity<Order> update(@RequestBody @Valid Order dto){
        return ResponseEntity.ok().body(service.update(dto));
    }

    @PutMapping("/starter/{id}")
    public ResponseEntity<Order> starterOrderService(@PathVariable("id") UUID id){
        Order result = service.starterOrderService(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/closed/{id}")
    public ResponseEntity<Order> closedOrderService(@PathVariable("id") UUID id){
        Order result = service.closedOrderService(id);
        return ResponseEntity.ok(result);
    }

}
