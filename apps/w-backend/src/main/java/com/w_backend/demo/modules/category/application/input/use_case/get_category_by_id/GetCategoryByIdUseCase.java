package com.w_backend.demo.modules.category.application.input.use_case.get_category_by_id;

import java.util.UUID;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface GetCategoryByIdUseCase {
    Category getCategoryById(UUID categoryId);
}
