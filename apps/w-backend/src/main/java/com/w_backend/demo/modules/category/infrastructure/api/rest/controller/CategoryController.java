package com.w_backend.demo.modules.category.infrastructure.api.rest.controller;

import org.springframework.stereotype.Controller;

import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("category") // Not sure if use this annotation or the one above
public class CategoryController {

    @PostMapping()
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return null;
    }
}
