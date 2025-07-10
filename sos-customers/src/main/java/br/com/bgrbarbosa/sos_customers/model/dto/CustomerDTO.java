package br.com.bgrbarbosa.sos_customers.model.dto;

import br.com.bgrbarbosa.mensages.ValidationMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    private UUID uuid;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 3, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String name;
    @CPF(message = ValidationMessage.INVALID_CPF)
    private String cpf;

    @Email(message = ValidationMessage.INVALID_EMAIL)
    private String email;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 12, max = 13, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String tel;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 13, max = 13, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String cel;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 3, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String address;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 2, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String neighborhood;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 3, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String city;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 2, max = 2, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String state;

    @NotBlank(message = ValidationMessage.NOT_BLANK)
    @Size(min = 8, max = 8, message = ValidationMessage.FIELD_SIZE_MESSAGE)
    private String cep;

}
