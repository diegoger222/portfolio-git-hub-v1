package com.w_backend.demo.modules.category.infrastructure.api.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w_backend.demo.modules.category.application.input.use_case.delete_category_by_id.DeleteCategoryByIdUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.get_all_categories.GetAllCategoriesUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.get_category_by_id.GetCategoryByIdUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.save_category.SaveCategoryUseCase;
import com.w_backend.demo.modules.category.domain.models.Category;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.UpdateCategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;
import com.w_backend.demo.modules.category.infrastructure.api.rest.mappers.CategoryRestApiMapper;
import com.w_backend.demo.common.services.kafka.service.KafkaSender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { CategoryController.class })
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SaveCategoryUseCase saveCategoryUseCase;

    @MockitoBean
    private GetCategoryByIdUseCase getCategoryByIdUseCase;

    @MockitoBean
    private GetAllCategoriesUseCase getAllCategoriesUseCase;

    @MockitoBean
    private DeleteCategoryByIdUseCase deleteCategoryByIdUseCase;

    @MockitoBean
    private CategoryRestApiMapper apiMapper;

    @MockitoBean
    private KafkaSender kafkaSender;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UUID categoryId;
    private Category sampleCategory;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();
        sampleCategory = new Category("Books", "A category for books");
    }

    @Test
    void shouldCreateCategorySuccessfully() throws Exception {
        final CategoryRequest request = new CategoryRequest(categoryId, "Books", "A category for books");

        when(saveCategoryUseCase.saveCategory(any())).thenReturn(sampleCategory);

        mockMvc.perform(post("/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Books"))
                .andExpect(jsonPath("$.description").value("A category for books"));
    }

    @Test
    void shouldGetCategoryById() throws Exception {
        when(getCategoryByIdUseCase.getCategoryById(categoryId)).thenReturn(sampleCategory);

        mockMvc.perform(get("/category/{id}", categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Books"))
                .andExpect(jsonPath("$.description").value("A category for books"));
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        List<Category> categories = Arrays.asList(
                new Category("Books", "Book category"),
                new Category("Movies", "Movie category"));

        when(getAllCategoriesUseCase.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/category/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Books"))
                .andExpect(jsonPath("$[1].name").value("Movies"));
    }

    @Test
    void shouldDeleteCategoryById() throws Exception {
        doNothing().when(deleteCategoryByIdUseCase).deleteCategoryById(categoryId);

        mockMvc.perform(delete("/category/{id}", categoryId))
                .andExpect(status().isOk());
    }

    @Test
    void shouldUpdateCategorySuccessfully() throws Exception {
        final UpdateCategoryRequest request = new UpdateCategoryRequest("Updated Books", "Updated description");
        final Category updatedCategory = new Category("Updated Books", "Updated description");

        when(saveCategoryUseCase.saveCategory(any())).thenReturn(updatedCategory);

        mockMvc.perform(put("/category/{id}", categoryId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Books"))
                .andExpect(jsonPath("$.description").value("Updated description"));
    }
}
