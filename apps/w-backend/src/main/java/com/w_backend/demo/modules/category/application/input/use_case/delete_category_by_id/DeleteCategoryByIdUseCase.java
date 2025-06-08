package com.w_backend.demo.modules.category.application.input.use_case.delete_category_by_id;

import java.util.UUID;

public interface DeleteCategoryByIdUseCase {
    void deleteCategoryById(UUID categoryId);
}
