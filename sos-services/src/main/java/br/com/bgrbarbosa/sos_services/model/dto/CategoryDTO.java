package br.com.bgrbarbosa.sos_services.model.dto;

import br.com.bgrbarbosa.mensages.ValidationMessage;
import br.com.bgrbarbosa.sos_services.model.BusinessService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {

        private UUID uuid;

        @NotBlank(message = ValidationMessage.NOT_BLANK)
        @Size(min = 3, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
        private String category;

        private List<BusinessService> service;
}
