package com.w_backend.demo.modules.category.application.input.use_case.get_category_by_id;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetCategoryByIdUseCaseImpl implements GetCategoryByIdUseCase {

    private final CategoriesRepository categoriesRepository;

    @Override
    public Category getCategoryById(UUID categoryId) {
        return categoriesRepository.findById(categoryId);
    }

}
