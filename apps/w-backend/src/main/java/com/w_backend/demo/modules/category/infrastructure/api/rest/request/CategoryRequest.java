package com.w_backend.demo.modules.category.infrastructure.api.rest.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private UUID id;
    private String name;
    private String description;
}
