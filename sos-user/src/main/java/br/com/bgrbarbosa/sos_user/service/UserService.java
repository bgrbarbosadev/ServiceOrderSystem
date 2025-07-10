package br.com.bgrbarbosa.sos_user.service;

import br.com.bgrbarbosa.sos_user.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User insert(User user);

    List<User> findAll();

    User findById(UUID id);

    User findByEmail(String email);

    void delete(UUID id);

    User update(User user);


}
