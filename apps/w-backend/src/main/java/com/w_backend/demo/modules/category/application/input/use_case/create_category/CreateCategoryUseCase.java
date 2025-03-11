package com.w_backend.demo.modules.category.application.input.use_case.create_category;

import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;

public interface CreateCategoryUseCase {

    // Need to create a new class request at application layer
    Category createCategory(CategoryRequest categoryRequest);
}
