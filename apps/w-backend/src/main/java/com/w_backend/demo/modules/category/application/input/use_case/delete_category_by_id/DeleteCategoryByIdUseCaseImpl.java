package com.w_backend.demo.modules.category.application.input.use_case.delete_category_by_id;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeleteCategoryByIdUseCaseImpl implements DeleteCategoryByIdUseCase {

    private final CategoriesRepository categoriesRepository;

    @Override
    public void deleteCategoryById(UUID categoryId) {
        categoriesRepository.deleteById(categoryId);
    }

}
