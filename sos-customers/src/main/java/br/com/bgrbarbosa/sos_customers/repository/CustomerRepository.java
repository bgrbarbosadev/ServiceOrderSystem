package br.com.bgrbarbosa.sos_customers.repository;

import br.com.bgrbarbosa.sos_customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
