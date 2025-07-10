package br.com.bgrbarbosa.sos_service_order.service;



import br.com.bgrbarbosa.sos_service_order.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order insert(Order order);

    List<Order> findAll();

    Order findById(UUID id);

    void delete(UUID id);

    Order update(Order order);

    Order starterOrderService(UUID id);

    Order closedOrderService(UUID id);

    Boolean findOrderByIdCustomer(UUID id);

    Boolean findOrderByIdService(UUID id);
}
