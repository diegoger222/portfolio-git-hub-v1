package com.w_backend.demo.modules.category.application.input.use_case.save_category;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SaveCategoryUseCaseTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private SaveCategoryUseCase saveCategoryUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory_CreatesAndSavesCategory() {
        final UUID id = UUID.randomUUID();
        final SaveCategoryRequest request = new SaveCategoryRequest(id, "Test", "Test description");

        Category expectedCategory = new Category("Test", "Test description");

        when(categoriesRepository.save(any(Category.class))).thenReturn(expectedCategory);

        final Category result = saveCategoryUseCase.saveCategory(request);

        // Assert
        assertNotNull(result);
        assertEquals("Test", result.getName());
        assertEquals("Test description", result.getDescription());

        verify(categoriesRepository, times(1)).save(any(Category.class));
    }
}
