package com.w_backend.demo.modules.category.infrastructure.persistence.repositories.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.w_backend.demo.modules.category.infrastructure.persistence.entities.CategoryJpaEntity;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, UUID> {

    // Additional query methods can be defined here if needed
    // For example, to find categories by name or description

    CategoryJpaEntity findByName(String name);

}
