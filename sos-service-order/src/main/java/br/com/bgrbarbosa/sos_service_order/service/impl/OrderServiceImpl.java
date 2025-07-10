package br.com.bgrbarbosa.sos_service_order.service.impl;


import br.com.bgrbarbosa.sos_service_order.enums.EnumStatus;
import br.com.bgrbarbosa.sos_service_order.model.Order;
import br.com.bgrbarbosa.sos_service_order.repository.OrderServiceRepository;
import br.com.bgrbarbosa.sos_service_order.service.OrderService;
import br.com.bgrbarbosa.sos_service_order.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderServiceRepository repository;

    @Override
    public Order insert(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID: " + id));
    }

    @Override
    public Boolean findOrderByIdCustomer(UUID id) {
        return (repository.findOrderByIdCustomer(id).isEmpty()) ? false : true;
    }

    @Override
    public Boolean findOrderByIdService(UUID id) {
        return (repository.findOrderByIdService(id).isEmpty()) ? false : true;
    }

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Order update(Order order) {
        Order aux = repository.findById(order.getUuid()).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found!"));
        aux.setCustomer(order.getCustomer());
        aux.setStatus(order.getStatus());
        aux.setDescription(order.getDescription());
        aux.setBusinessService(order.getBusinessService());
        return repository.save(aux);
    }

    @Override
    public Order starterOrderService(UUID id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID: " + id));
        order.setStatus(EnumStatus.IN_PROGRESS);
        return repository.save(order);
    }

    @Override
    public Order closedOrderService(UUID id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID: " + id));
        order.setStatus(EnumStatus.CLOSE);
        return repository.save(order);
    }


}
