package com.w_backend.demo.modules.category.infrastructure.api.rest.mappers;

import com.w_backend.demo.modules.category.application.input.use_case.save_category.SaveCategoryRequest;
import com.w_backend.demo.modules.category.domain.models.Category;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.UpdateCategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;

@Mapper
public interface CategoryRestApiMapper {

    CategoryRestApiMapper INSTANCE = Mappers.getMapper(CategoryRestApiMapper.class);

    // Map the domain model to the response
    CategoryResponse domainModelToCategoryResponse(Category category);

    SaveCategoryRequest categoryResponseToCreateCategoryRequest(CategoryRequest categoryRequest);

    SaveCategoryRequest updateCategoryRequestToSaveCategoryRequest(
            UUID id,
            UpdateCategoryRequest updateCategoryRequest);
}
