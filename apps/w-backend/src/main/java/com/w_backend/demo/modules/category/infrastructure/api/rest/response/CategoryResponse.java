package com.w_backend.demo.modules.category.infrastructure.api.rest.response;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CategoryResponse {
    private UUID id;
    private String name;
    private String description;
}
