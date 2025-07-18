package com.w_backend.demo.modules.category.infrastructure.api.rest.request;

import java.util.UUID;

import io.micrometer.common.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    @Nullable // This field can be null if the category is new and does not have an ID yet
    private UUID id;
    private String name;
    private String description;
}
