package br.com.bgrbarbosa.sos_services.service;

import br.com.bgrbarbosa.sos_services.model.BusinessService;

import java.util.List;
import java.util.UUID;

public interface ServiceOperations {

    BusinessService insert(BusinessService service) throws Exception;

    List<BusinessService> findAll();

    BusinessService findById(UUID id);

    BusinessService findByService(String service);

    void delete(UUID id) throws Exception;

    BusinessService update(BusinessService service) throws Exception;

}
