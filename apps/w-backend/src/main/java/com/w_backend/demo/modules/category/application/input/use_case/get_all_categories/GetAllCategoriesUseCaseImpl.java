package com.w_backend.demo.modules.category.application.input.use_case.get_all_categories;

import java.util.List;

import org.springframework.stereotype.Service;

import com.w_backend.demo.modules.category.application.output.CategoriesRepository;
import com.w_backend.demo.modules.category.domain.models.Category;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GetAllCategoriesUseCaseImpl implements GetAllCategoriesUseCase {

    private final CategoriesRepository categoriesRepository;

    @Override
    public List<Category> getAllCategories() {

        return categoriesRepository.findAll();
    }

}
