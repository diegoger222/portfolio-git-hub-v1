package com.w_backend.demo.modules.category.application.input.use_case.get_category_by_id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetCategoryByIdUseCaseTest {

    @Mock
    private CategoriesRepository categoriesRepository;

    @InjectMocks
    private GetCategoryByIdUseCaseImpl getCategoryByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCategoryWhenFoundById() {
        final UUID categoryId = UUID.randomUUID();
        final Category expectedCategory = new Category("Books", "Books category");

        when(categoriesRepository.findById(categoryId)).thenReturn(expectedCategory);

        final Category actualCategory = getCategoryByIdUseCase.getCategoryById(categoryId);

        assertNotNull(actualCategory);
        assertEquals(expectedCategory.getName(), actualCategory.getName());
        assertEquals(expectedCategory.getDescription(), actualCategory.getDescription());
        verify(categoriesRepository, times(1)).findById(categoryId);
    }
}
