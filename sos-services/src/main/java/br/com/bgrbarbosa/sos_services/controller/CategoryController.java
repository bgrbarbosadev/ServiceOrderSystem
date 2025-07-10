package br.com.bgrbarbosa.sos_services.controller;


import br.com.bgrbarbosa.sos_services.controller.mapper.CategoryMapper;
import br.com.bgrbarbosa.sos_services.model.Category;
import br.com.bgrbarbosa.sos_services.model.dto.CategoryDTO;
import br.com.bgrbarbosa.sos_services.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    private final CategoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> searchAll(){
        List<Category> list = service.findAll();
        List<CategoryDTO> listDTO = mapper.parseToListDTO(list);
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> searchById(@PathVariable("id") UUID id){
        CategoryDTO result = mapper.parseToDto(service.findById(id));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryDTO> searchByCategory(@RequestParam String category){
        CategoryDTO result = mapper.parseToDto(service.findByCategory(category));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody @Valid CategoryDTO dto){
        Category result = service.insert(mapper.parseToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.parseToDto(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id){
        service.delete(id);
        return ResponseEntity.ok().body("Successfully deleted!!");
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid CategoryDTO dto){
        Category aux = mapper.parseToEntity(dto);
        return ResponseEntity.ok().body(mapper.parseToDto(service.update(aux)));
    }



}
