package com.w_backend.demo.modules.category.domain.models;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private UUID id;
    private String name;
    private String description;

    public Category(String name, String description) {
        // this.id = UUID.randomUUID(); let the database handle the ID generation
        this.name = name;
        this.description = description;
    }
}
