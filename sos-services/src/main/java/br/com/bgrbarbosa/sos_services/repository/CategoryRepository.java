package br.com.bgrbarbosa.sos_services.repository;

import br.com.bgrbarbosa.sos_services.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    boolean existsByCategory(String category);
    Category findByCategory(String category);
}
