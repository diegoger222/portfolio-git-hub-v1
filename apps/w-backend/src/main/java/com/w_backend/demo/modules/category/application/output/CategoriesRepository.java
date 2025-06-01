package com.w_backend.demo.modules.category.application.output;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface CategoriesRepository {
    Category save(Category category);

}
