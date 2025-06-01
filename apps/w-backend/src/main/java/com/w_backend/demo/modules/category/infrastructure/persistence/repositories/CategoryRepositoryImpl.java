package com.w_backend.demo.modules.category.infrastructure.persistence.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.persistence.entities.CategoryJpaEntity;
import com.w_backend.demo.modules.category.infrastructure.persistence.mappers.CategoryJpaMapper;
import com.w_backend.demo.modules.category.infrastructure.persistence.repositories.jpa.CategoryJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoriesRepository {

    private static final CategoryJpaMapper jpaMapper = CategoryJpaMapper.INSTANCE;

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public Category save(Category category) {

        final CategoryJpaEntity categoryJpaEntity = jpaMapper.domainModelToCategoryJpaEntity(category);

        final CategoryJpaEntity storedcategoryJpaEntity = categoryJpaEntity.getId() == null
                ? categoryJpaRepository.saveAndFlush(categoryJpaEntity)
                : categoryJpaRepository.save(categoryJpaEntity);

        return jpaMapper.categoryJpaEntityToDomainModel(storedcategoryJpaEntity);

    }

    @Override
    public Category findById(UUID id) {
        // Add Custom exception
        final CategoryJpaEntity categoryJpaEntity = categoryJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));

        return jpaMapper.categoryJpaEntityToDomainModel(categoryJpaEntity);
    }

    @Override
    public void deleteById(UUID id) {
        categoryJpaRepository.deleteById(id);
    }

    @Override
    public List<Category> findAll() {
        final List<CategoryJpaEntity> categoryJpaEntities = categoryJpaRepository.findAll();

        return categoryJpaEntities.stream()
                .map(jpaMapper::categoryJpaEntityToDomainModel)
                .toList();
    }

}
