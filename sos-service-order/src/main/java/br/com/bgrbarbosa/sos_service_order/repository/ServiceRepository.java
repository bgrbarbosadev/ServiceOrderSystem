package br.com.bgrbarbosa.sos_service_order.repository;

import br.com.bgrbarbosa.sos_service_order.model.BusinessService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface ServiceRepository extends JpaRepository<BusinessService, UUID> {

}
