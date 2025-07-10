package br.com.bgrbarbosa.sos_service_order.service.impl;


import br.com.bgrbarbosa.sos_service_order.enums.EnumStatus;
import br.com.bgrbarbosa.sos_service_order.model.Order;
import br.com.bgrbarbosa.sos_service_order.repository.CustomerRepository;
import br.com.bgrbarbosa.sos_service_order.repository.OrderServiceRepository;
import br.com.bgrbarbosa.sos_service_order.service.CustomerService;
import br.com.bgrbarbosa.sos_service_order.service.OrderService;
import br.com.bgrbarbosa.sos_service_order.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    @Override
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ID not found: " + id);
        }
        repository.deleteById(id);
    }
}
