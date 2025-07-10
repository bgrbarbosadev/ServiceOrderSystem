package br.com.bgrbarbosa.sos_customers.controller.mapper;

import br.com.bgrbarbosa.sos_customers.model.Customer;
import br.com.bgrbarbosa.sos_customers.model.dto.CustomerDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer parseToEntity(CustomerDTO dto);

    CustomerDTO parseToDto(Customer entity);

    List<CustomerDTO> parseToListDTO(List<Customer>list);
}
