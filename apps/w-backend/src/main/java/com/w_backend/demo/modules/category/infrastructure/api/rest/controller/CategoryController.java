package com.w_backend.demo.modules.category.infrastructure.api.rest.controller;

import com.w_backend.demo.modules.category.application.input.use_case.create_category.CreateCategoryUseCase;
import com.w_backend.demo.modules.category.infrastructure.api.rest.mappers.CategoryRestApiMapper;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;
import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    private static final CategoryRestApiMapper apiMapper = CategoryRestApiMapper.INSTANCE;

    @PostMapping("/create")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {

        Category category = createCategoryUseCase
                .createCategory(apiMapper.categoryResponseToCreateCategoryRequest(categoryRequest));

        return apiMapper.domainModelToCategoryResponse(category);
    }

    @GetMapping("/get")
    public String getMethodName() {
        return "patata";
    }

}
