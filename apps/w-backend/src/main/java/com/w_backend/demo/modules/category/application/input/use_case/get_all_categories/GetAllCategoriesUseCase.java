package com.w_backend.demo.modules.category.application.input.use_case.get_all_categories;

import java.util.List;

import com.w_backend.demo.modules.category.domain.models.Category;

public interface GetAllCategoriesUseCase {
    List<Category> getAllCategories();
}
