package com.w_backend.demo.modules.category.application.input.use_case.save_category;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaveCategoryUseCaseImpl implements SaveCategoryUseCase {

    @Override
    public Category saveCategory(SaveCategoryRequest createCategoryRequest) {

        return new Category(createCategoryRequest.getName(), createCategoryRequest.getDescription());
        // create the repo, maybe poostgress
    }

}
