package com.example.product.repository;

import com.example.product.model.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProductRepository extends ReactiveMongoRepository<Product, UUID>, ReactiveQuerydslPredicateExecutor<Product>{


    @Query("{categoryId:'?0'}")
    Flux<Product> findProductByCategory(String categoryId);
}
