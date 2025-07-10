package br.com.bgrbarbosa.sos_user.controller.mapper;



import br.com.bgrbarbosa.sos_user.model.User;
import br.com.bgrbarbosa.sos_user.model.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User parseToEntity(UserDTO dto);

    UserDTO parseToDto(User entity);

    List<UserDTO> parseToListDTO(List<User>list);
}
