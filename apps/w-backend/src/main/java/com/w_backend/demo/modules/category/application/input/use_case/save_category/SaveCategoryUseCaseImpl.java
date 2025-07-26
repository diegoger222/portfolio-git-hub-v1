package com.w_backend.demo.modules.category.application.input.use_case.save_category;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveCategoryUseCaseImpl implements SaveCategoryUseCase {

    private final CategoriesRepository categoriesRepository;

    @Override
    public Category saveCategory(SaveCategoryRequest createCategoryRequest) {

        final Category category = new Category(createCategoryRequest.getName(), createCategoryRequest.getDescription());

        return categoriesRepository.save(category);
    }
}
