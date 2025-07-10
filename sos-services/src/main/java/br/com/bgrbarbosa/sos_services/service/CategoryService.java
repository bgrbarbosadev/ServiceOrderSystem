package br.com.bgrbarbosa.sos_services.service;



import br.com.bgrbarbosa.sos_services.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category insert(Category category);

    List<Category> findAll();

    Category findById(UUID id);

    Category findByCategory(String category);

    void delete(UUID id);

    Category update(Category category);

}
