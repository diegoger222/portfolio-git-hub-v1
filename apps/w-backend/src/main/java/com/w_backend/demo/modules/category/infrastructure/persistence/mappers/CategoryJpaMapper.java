package com.w_backend.demo.modules.category.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.persistence.entities.CategoryJpaEntity;

@Mapper
public interface CategoryJpaMapper {

        CategoryJpaMapper INSTANCE = Mappers.getMapper(CategoryJpaMapper.class);

        // Map the domain model to the JPA entity
        @Mapping(target = "createdAt", ignore = true) // createdAt will be set automatically
        CategoryJpaEntity domainModelToCategoryJpaEntity(Category category);

        // Map the JPA entity to the domain model
        Category categoryJpaEntityToDomainModel(CategoryJpaEntity categoryJpaEntity);
}
