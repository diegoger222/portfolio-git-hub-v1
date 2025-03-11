package com.w_backend.demo.modules.category.application.input.use_case.create_category;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {

        return new Category(categoryRequest.getName(), categoryRequest.getDescription());
        // create the repo, maybe poostgress
    }

}
