package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductService {

    Mono<ProductDTO> saveProduct(ProductDTO productDTO);

    Mono<ProductDTO> updateProduct(ProductDTO productDTO);

    Mono<Void> deleteProductById(UUID id);

    Mono<ProductDTO> findProductById(UUID id, String countryCode);

    Flux<Void> deleteProductByCategoryId(String id);

    Flux<Product> findAll();

    Flux<Product> findByCategoryId(String categoryId);
}
