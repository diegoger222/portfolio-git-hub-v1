package com.w_backend.demo.modules.category.domain.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    void testConstructorSetsFields() {
        final String name = "Magic";
        final String description = "Test for category";

        final Category category = new Category(name, description);

        assertEquals(name, category.getName());
        assertEquals(description, category.getDescription());
        assertNull(category.getId());
    }

    @Test
    void testSettersAndGetters() {
        Category category = new Category("Inicial", "Desc");

        final UUID id = UUID.randomUUID();
        final String newName = "Test";
        final String newDescription = "Category for testing";

        category.setId(id);
        category.setName(newName);
        category.setDescription(newDescription);

        assertEquals(id, category.getId());
        assertEquals(newName, category.getName());
        assertEquals(newDescription, category.getDescription());
    }
}