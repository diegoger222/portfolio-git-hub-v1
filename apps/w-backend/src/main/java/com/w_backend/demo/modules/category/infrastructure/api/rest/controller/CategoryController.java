package com.w_backend.demo.modules.category.infrastructure.api.rest.controller;

import com.w_backend.demo.common.services.kafka.service.KafkaSender;
import com.w_backend.demo.modules.category.application.input.use_case.delete_category_by_id.DeleteCategoryByIdUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.get_all_categories.GetAllCategoriesUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.get_category_by_id.GetCategoryByIdUseCase;
import com.w_backend.demo.modules.category.application.input.use_case.save_category.SaveCategoryUseCase;
import com.w_backend.demo.modules.category.infrastructure.api.rest.mappers.CategoryRestApiMapper;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.request.UpdateCategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;
import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final SaveCategoryUseCase saveCategoryUseCase;

    private final GetCategoryByIdUseCase getCategoryByIdUseCase;

    private final GetAllCategoriesUseCase getAllCategoriesUseCase;

    private final DeleteCategoryByIdUseCase deleteCategoryUseCase;

    private final KafkaSender kafkaSender;

    private static final CategoryRestApiMapper apiMapper = CategoryRestApiMapper.INSTANCE;

    @PostMapping("/create")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = saveCategoryUseCase
                .saveCategory(apiMapper.categoryResponseToCreateCategoryRequest(categoryRequest));

        return apiMapper.domainModelToCategoryResponse(category);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable UUID id) {
        return apiMapper.domainModelToCategoryResponse(getCategoryByIdUseCase.getCategoryById(id));
    }

    // @RequestParam(required = false) String name THis is an example of how to use
    @GetMapping("/all")
    public CategoryResponse[] getAllCategories() {
        return getAllCategoriesUseCase.getAllCategories()
                .stream()
                .map(apiMapper::domainModelToCategoryResponse)
                .toArray(CategoryResponse[]::new);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable UUID id) {
        deleteCategoryUseCase.deleteCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse putMethodName(@PathVariable UUID id, @RequestBody UpdateCategoryRequest entity) {

        Category category = saveCategoryUseCase.saveCategory(
                apiMapper.updateCategoryRequestToSaveCategoryRequest(id, entity));

        return apiMapper.domainModelToCategoryResponse(category);
    }

    @PostMapping("/kafka-test")
    public void kafkaTest() {
        kafkaSender.sendMessage("Hello Kafka Patatas", "topic-1");
    }

    @GetMapping("/get")
    public String getMethodName() {
        return "patata";
    }

}
