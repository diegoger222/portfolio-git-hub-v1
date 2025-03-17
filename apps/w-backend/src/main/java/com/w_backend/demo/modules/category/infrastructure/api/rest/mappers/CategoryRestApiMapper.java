package com.w_backend.demo.modules.category.infrastructure.api.rest.mappers;

import java.util.Locale.Category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;

@Mapper
public interface CategoryRestApiMapper {

    CategoryRestApiMapper INSTANCE = Mappers.getMapper(CategoryRestApiMapper.class);

    // Map the domain model to the response
    CategoryResponse domainModelToCategoryResponse(Category category);

    // Map the request to the domain model
    Category categoryRequestToDomainModel(CategoryRequest categoryRequest);
}
