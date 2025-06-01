package com.w_backend.demo.modules.category.infrastructure.persistence.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpaEntity {
    public static final String TABLE_NAME = "categories";

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String DESCRIPTION_COLUMN = "description";

    @Id
    @Column(name = ID_COLUMN, nullable = false, unique = true)
    private UUID id;

    @Column(name = NAME_COLUMN, nullable = false)
    private String name;

    @Column(name = DESCRIPTION_COLUMN)
    private String description;
}
