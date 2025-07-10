package br.com.bgrbarbosa.sos_service_order.repository;

import br.com.bgrbarbosa.sos_service_order.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
