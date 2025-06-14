package com.w_backend.demo.modules.category.application.output;

import java.util.List;
import java.util.UUID;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface CategoriesRepository {
    Category save(Category category);

    Category findById(UUID id);

    void deleteById(UUID id);

    List<Category> findAll();
}
