package com.example.product.model;


import com.example.product.base.AbstractMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("Product")
public class Product extends AbstractMongoEntity<UUID> {

    @NotBlank(message = "Product Name can not be null")
    private String name;

    private String description;

    private String image ;

    @NotBlank(message = "CategoryId can not be null")
    private String categoryId;

    @Indexed(unique = true)
    private String sku;

    private String browsingName;

    private double price;

    private double listPrice;

    private int quantity;

    private Boolean isStockControlled;

    private Status status;

    private int rank;

}

