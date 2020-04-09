package com.example.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotBlank(message = "Product Name can not be null")
    private String name;

    private String description;

    private String image ;

    @NotBlank(message = "CategoryId can not be null")
    private String categoryId;

    private String sku;

    private String browsingName;

    private double price;

    private double listPrice;

    private int quantity;

    private Boolean isStockControlled;

    private int status;

    private int rank;
}
