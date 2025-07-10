package br.com.bgrbarbosa.sos_user.model.dto;



import br.com.bgrbarbosa.mensages.ValidationMessage;
import br.com.bgrbarbosa.sos_user.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

        private UUID uuid;

        @NotBlank(message = ValidationMessage.NOT_BLANK)
        @Size(min = 3, max = 50, message = ValidationMessage.FIELD_SIZE_MESSAGE)
        private String name;

        @Email(message = ValidationMessage.EMAIL_VALID)
        private String email;

        @NotBlank(message = ValidationMessage.NOT_BLANK)
        @Size(min = 6, max = 12, message = ValidationMessage.FIELD_SIZE_MESSAGE)
        private String password;

        @NotNull(message = ValidationMessage.NOT_BLANK)
        private Role role;
}
