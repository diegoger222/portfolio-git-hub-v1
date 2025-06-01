package com.w_backend.demo.modules.category.application.input.use_case.create_category;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateCategoryUseCaseImpl implements CreateCategoryUseCase {

    @Override
    public Category createCategory(CreateCategoryRequest createCategoryRequest) {

        return new Category(createCategoryRequest.getName(), createCategoryRequest.getDescription());
        // create the repo, maybe poostgress
    }

}
