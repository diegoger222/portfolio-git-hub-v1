package com.w_backend.demo.modules.category.application.input.use_case.delete_category_by_id;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteCategoryByIdUseCaseTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private DeleteCategoryByIdUseCaseImpl deleteCategoryByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallRepositoryDeleteByIdOnceWithCorrectId() {
        final UUID categoryId = UUID.randomUUID();

        deleteCategoryByIdUseCase.deleteCategoryById(categoryId);

        verify(categoriesRepository, times(1)).deleteById(categoryId);
    }
}