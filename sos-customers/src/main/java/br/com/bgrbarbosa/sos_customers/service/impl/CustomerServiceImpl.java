package br.com.bgrbarbosa.sos_customers.service.impl;

import br.com.bgrbarbosa.mensages.ValidationMessage;
import br.com.bgrbarbosa.sos_customers.infra.UpdateCustomerServiceOrder;
import br.com.bgrbarbosa.sos_customers.model.Customer;
import br.com.bgrbarbosa.sos_customers.repository.CustomerRepository;
import br.com.bgrbarbosa.sos_customers.service.CustomerService;
import br.com.bgrbarbosa.sos_customers.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ResponseBody
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository repository;
    private final UpdateCustomerServiceOrder updateCustomerServiceOrder;

    @Override
    public Customer insert(Customer customer) throws Exception {
        Customer result = new Customer();
        try{
            result = repository.save(customer);
            updateCustomerServiceOrder.updateCustomer(result);
        } catch (Exception e) {
            throw new Exception(ValidationMessage.ERROR_INSERTING_RECORD + result.getUuid());
        }
        return result;
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND + id));
    }

    @Override
    public void delete(UUID id) throws Exception {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND + id);
        }

        try {
            repository.deleteById(id);
            updateCustomerServiceOrder.deleteCustomer(id);
        } catch (Exception e) {
            throw new Exception(ValidationMessage.ERROR_DELETION_RECORD + id + " - " + e.getMessage());
        }
    }

    @Override
    public Customer update(Customer customer) throws Exception {
        Customer aux = repository.findById(customer.getUuid()).orElseThrow(
                () -> new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND));
        aux.setEmail(customer.getEmail());
        aux.setName(customer.getName());
        aux.setCity(customer.getCity());
        aux.setCep(customer.getCep());
        aux.setCel(customer.getCel());
        aux.setAddress(customer.getAddress());
        aux.setState(customer.getState());
        aux.setTel(customer.getTel());
        aux.setNeighborhood(customer.getNeighborhood());
        aux.setCpf(customer.getCpf());
        Customer result = new Customer();
        try{
            result = repository.save(aux);
            updateCustomerServiceOrder.updateCustomer(result);
        } catch (Exception e) {
            throw new Exception(ValidationMessage.ERROR_INSERTING_RECORD + result.getUuid());
        }
        return result;
    }

}
