package com.w_backend.demo.modules.category.application.input.use_case.create_category;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface CreateCategoryUseCase {

    Category createCategory(CreateCategoryRequest createCategoryRequest);
}
