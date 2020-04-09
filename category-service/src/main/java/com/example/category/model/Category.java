package com.example.category.model;

import com.example.category.base.AbstractMongoEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "categories")
@TypeAlias(value = "Category")
public class Category extends AbstractMongoEntity<UUID> {

    @Indexed(unique = true)
    private String name;

    private String description;

    @NotBlank(message = "industryId is required")
    private String industryId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ObjectId parentCategoryId;


}
