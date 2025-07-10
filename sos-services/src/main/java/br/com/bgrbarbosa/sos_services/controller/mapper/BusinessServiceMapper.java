package br.com.bgrbarbosa.sos_services.controller.mapper;

import br.com.bgrbarbosa.sos_services.model.BusinessService;
import br.com.bgrbarbosa.sos_services.model.dto.BusinessServiceDTO;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BusinessServiceMapper {

    BusinessService parseToEntity(BusinessServiceDTO dto);

    BusinessServiceDTO parseToDto(BusinessService entity);

    List<BusinessServiceDTO> parseToListDTO(List<BusinessService>list);
}
