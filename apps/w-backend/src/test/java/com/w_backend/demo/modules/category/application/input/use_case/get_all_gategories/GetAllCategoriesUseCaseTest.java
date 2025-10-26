package com.w_backend.demo.modules.category.application.input.use_case.get_all_gategories;

import com.w_backend.demo.modules.category.application.input.use_case.get_all_categories.GetAllCategoriesUseCaseImpl;
import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetAllCategoriesUseCaseTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private GetAllCategoriesUseCaseImpl getAllCategoriesUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllCategoriesFromRepository() {
        final Category category1 = new Category("Tech", "Technology category");
        final Category category2 = new Category("Health", "Health category");
        final List<Category> mockCategories = Arrays.asList(category1, category2);

        when(categoriesRepository.findAll()).thenReturn(mockCategories);

        final List<Category> result = getAllCategoriesUseCase.getAllCategories();

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Tech", result.get(0).getName());
        assertEquals("Health", result.get(1).getName());

        verify(categoriesRepository, times(1)).findAll();
    }
}
