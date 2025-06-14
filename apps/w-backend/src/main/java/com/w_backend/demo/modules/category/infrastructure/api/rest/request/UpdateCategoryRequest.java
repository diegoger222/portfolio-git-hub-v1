package com.w_backend.demo.modules.category.infrastructure.api.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UpdateCategoryRequest {
    private String name;
    private String description;
}
