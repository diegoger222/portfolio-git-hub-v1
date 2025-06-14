package com.w_backend.demo.modules.category.application.input.use_case.save_category;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface SaveCategoryUseCase {

    Category saveCategory(SaveCategoryRequest createCategoryRequest);
}
