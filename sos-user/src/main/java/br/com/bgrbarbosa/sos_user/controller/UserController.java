package br.com.bgrbarbosa.sos_user.controller;

import br.com.bgrbarbosa.sos_user.controller.mapper.UserMapper;
import br.com.bgrbarbosa.sos_user.model.User;
import br.com.bgrbarbosa.sos_user.model.dto.UserDTO;
import br.com.bgrbarbosa.sos_user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> searchAll(){
        List<UserDTO> listDTO = mapper.parseToListDTO(service.findAll());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> searchById(@PathVariable("id") UUID id){
        UserDTO result = mapper.parseToDto(service.findById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> searchByEmail(@RequestParam String email){
        UserDTO result = mapper.parseToDto(service.findByEmail(email));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTO dto){
        User result = service.insert(mapper.parseToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.parseToDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponseEntity.ok().body("Successfully deleted!!");
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO dto){
        User aux = mapper.parseToEntity(dto);
        return ResponseEntity.ok().body(mapper.parseToDto(service.update(aux)));
    }



}
