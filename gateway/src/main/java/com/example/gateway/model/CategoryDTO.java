package com.example.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private UUID id;
    @NotBlank(message = "Name can not be null")
    private String name;
    private String description;
}
