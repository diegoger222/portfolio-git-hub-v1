package com.w_backend.demo.modules.category.infrastructure.persistence.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.UUID;

import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.persistence.repositories.jpa.CategoryJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    private CategoryRepositoryImpl categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = new CategoryRepositoryImpl(categoryJpaRepository);
    }

    @Test
    void saveAndFindById() {
        final Category category = new Category("Test", "Test description");

        final Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test");

        final Category found = categoryRepository.findById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Test");
    }

    @Test
    void findByIdThrowsExceptionWhenNotFound() {
        final UUID randomId = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () -> {
            categoryRepository.findById(randomId);
        });
    }

    @Test
    void findAllReturnsAllCategories() {
        categoryRepository.save(new Category("Cat1", "Desc1"));
        categoryRepository.save(new Category("Cat2", "Desc2"));

        final List<Category> categories = categoryRepository.findAll();

        assertThat(categories).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void deleteByIdDeletesCategory() {
        final Category category = categoryRepository.save(new Category("ToDelete", "Desc"));

        categoryRepository.deleteById(category.getId());

        final UUID id = category.getId();
        assertThrows(IllegalArgumentException.class, () -> categoryRepository.findById(id));

    }
}
