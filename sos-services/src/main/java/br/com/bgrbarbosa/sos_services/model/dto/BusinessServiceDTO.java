package br.com.bgrbarbosa.sos_services.model.dto;

import br.com.bgrbarbosa.mensages.ValidationMessage;
import br.com.bgrbarbosa.sos_services.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BusinessServiceDTO {

        private UUID uuid;

        @NotBlank(message = ValidationMessage.NOT_BLANK)
        @Size(min = 5, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
        private String description;

        @NotNull(message = ValidationMessage.NOT_NULL)
        private Double vl_service;

        @NotNull(message = ValidationMessage.NOT_NULL)
        private Category category;
}

