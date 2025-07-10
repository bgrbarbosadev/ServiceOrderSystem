package br.com.bgrbarbosa.sos_user.service.impl;

import br.com.bgrbarbosa.sos_user.model.User;
import br.com.bgrbarbosa.sos_user.repository.UserRepository;
import br.com.bgrbarbosa.sos_user.service.UserService;
import br.com.bgrbarbosa.sos_user.service.exception.ResourceNotFoundException;
import br.com.bgrbarbosa.sos_user.service.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@ResponseBody
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User insert(User user) {
        User aux = user;
        if (userRepository.existsByEmail(aux.getEmail())) {
            throw new UserException("{user-exception}");
        }
        aux.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID: " + id));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID not found: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User update(User user) {
        User aux = userRepository.findById(user.getUuid()).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found!"));
        aux.setName(user.getName());
        aux.setEmail(user.getEmail());
        aux.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        aux.setRole(user.getRole());
        return userRepository.save(aux);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
