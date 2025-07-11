package br.com.bgrbarbosa.sos_services.service.impl;


import br.com.bgrbarbosa.mensages.ValidationMessage;
import br.com.bgrbarbosa.sos_services.infra.UpdateItemServiceOrder;
import br.com.bgrbarbosa.sos_services.model.BusinessService;
import br.com.bgrbarbosa.sos_services.repository.ServiceOperationsRepository;
import br.com.bgrbarbosa.sos_services.service.ServiceOperations;
import br.com.bgrbarbosa.sos_services.service.exception.EntityException;
import br.com.bgrbarbosa.sos_services.service.exception.ResourceNotFoundException;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;


@Service
public class ServiceOperationsImpl implements ServiceOperations {

    @Autowired
    private ServiceOperationsRepository repository;

    @Autowired
    private UpdateItemServiceOrder updateItemServiceOrder;

    @Override
    public BusinessService insert(BusinessService businessService) throws Exception {
        BusinessService result = new BusinessService();
        try {
          result = repository.save(businessService);
          updateItemServiceOrder.updateService(result);
        } catch (Exception e) {
            throw new Exception(ValidationMessage.ERROR_INSERTING_RECORD + result.getUuid());
        }
        return result;
    }

    @Override
    public List<BusinessService> findAll() {
        return repository.findAll();
    }

    @Override
    public BusinessService findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND + id));
    }

    @Override
    public BusinessService findByService(String service) {
        return repository.findByDescription(service);
    }

    @Override
    public void delete(UUID id) throws Exception {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND + id);
        }

        try{
            repository.deleteById(id);
            updateItemServiceOrder.deleteCustomer(id);

        } catch (Exception e){
            throw new Exception(ValidationMessage.ERROR_DELETION_RECORD + id + " - " + e.getMessage());
        }
    }


    @Override
    public BusinessService update(BusinessService service) throws Exception {
        BusinessService aux = repository.findById(service.getUuid()).orElseThrow(
                () -> new ResourceNotFoundException(ValidationMessage.RESOURCE_NOT_FOUND));
        aux.setDescription(service.getDescription());
        aux.setVl_service(service.getVl_service());
        aux.setCategory(service.getCategory());
        BusinessService result = new BusinessService();
        try {
            result = repository.save(aux);
            updateItemServiceOrder.updateService(result);
        } catch (Exception e){
            throw new Exception(ValidationMessage.ERROR_INSERTING_RECORD + result.getUuid());
        }
        return result;
    }
}
