package br.com.bgrbarbosa.sos_customers.service;


import br.com.bgrbarbosa.sos_customers.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer insert(Customer customer) throws Exception;

    List<Customer> findAll();

    Customer findById(UUID id);

    void delete(UUID id) throws Exception;

    Customer update(Customer customer) throws Exception;


}
