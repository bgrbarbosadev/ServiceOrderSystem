package br.com.bgrbarbosa.sos_services.repository;

import br.com.bgrbarbosa.sos_services.model.BusinessService;
import br.com.bgrbarbosa.sos_services.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceOperationsRepository extends JpaRepository<BusinessService, UUID> {

    BusinessService findByDescription(String description);
}
