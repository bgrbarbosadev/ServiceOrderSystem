package br.com.bgrbarbosa.sos_service_order.repository;


import br.com.bgrbarbosa.sos_service_order.model.Order;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;


public interface OrderServiceRepository extends JpaRepository<Order, UUID> {

    @Query(value = "SELECT o FROM Order o WHERE o.customer.uuid = :customerId")
    List<Order> findOrderByIdCustomer(@Param("customerId") UUID customerId);

    @Query(value = "SELECT o FROM Order o JOIN o.businessService bs WHERE bs.uuid = :serviceId")
    List<Order> findOrderByIdService(@Param("serviceId") UUID serviceId);
}
