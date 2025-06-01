package com.w_backend.demo.modules.category.application.input.use_case.create_category;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CreateCategoryRequest {
    private UUID id;
    private String name;
    private String description;
}
