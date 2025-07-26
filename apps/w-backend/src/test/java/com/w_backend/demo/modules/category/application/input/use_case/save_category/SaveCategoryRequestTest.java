package com.w_backend.demo.modules.category.application.input.use_case.save_category;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class SaveCategoryRequestTest {

    @Test
    void testConstructorAndGetters() {
        final UUID id = UUID.randomUUID();
        final String name = "Test";
        final String description = "Test description";

        final SaveCategoryRequest request = new SaveCategoryRequest(id, name, description);

        assertEquals(id, request.getId());
        assertEquals(name, request.getName());
        assertEquals(description, request.getDescription());
    }

    @Test
    void testSetters() {
        SaveCategoryRequest request = new SaveCategoryRequest(null, null, null);

        final UUID id = UUID.randomUUID();
        request.setId(id);
        request.setName("Test");
        request.setDescription("Caregory test");

        assertEquals(id, request.getId());
        assertEquals("Test", request.getName());
        assertEquals("Caregory test", request.getDescription());
    }
}
