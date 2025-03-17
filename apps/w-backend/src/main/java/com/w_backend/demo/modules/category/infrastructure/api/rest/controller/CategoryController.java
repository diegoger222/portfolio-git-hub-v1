package com.w_backend.demo.modules.category.infrastructure.api.rest.controller;

import org.springframework.stereotype.Controller;

import com.w_backend.demo.modules.category.infrastructure.api.rest.request.CategoryRequest;
import com.w_backend.demo.modules.category.infrastructure.api.rest.response.CategoryResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RestController
@RequestMapping("/category")
public class CategoryController {

    @PostMapping("/create")
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
        return null;
    }

    @GetMapping("/get")
    public String getMethodName() {
        return "patata";
    }

}
