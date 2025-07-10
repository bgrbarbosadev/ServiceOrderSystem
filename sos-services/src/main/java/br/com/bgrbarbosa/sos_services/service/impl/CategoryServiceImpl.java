package br.com.bgrbarbosa.sos_services.service.impl;

import br.com.bgrbarbosa.sos_services.model.Category;
import br.com.bgrbarbosa.sos_services.repository.CategoryRepository;
import br.com.bgrbarbosa.sos_services.service.CategoryService;
import br.com.bgrbarbosa.sos_services.service.exception.EntityException;
import br.com.bgrbarbosa.sos_services.service.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category insert(Category category) {
        Category aux = category;
        if (categoryRepository.existsByCategory(category.getCategory())) {
            throw new EntityException("{entity-exception}");
        }
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("ID: " + id));
    }

    @Override
    public Category findByCategory(String category) {
        return categoryRepository.findByCategory(category);
    }

    @Override
    public void delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(Category category) {
        Category aux = categoryRepository.findById(category.getUuid()).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found!"));
        aux.setCategory(category.getCategory());
        return categoryRepository.save(aux);
    }
}
