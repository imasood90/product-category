package com.example.category.repository;

import com.example.category.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface CategoryRepository extends ReactiveMongoRepository<Category, UUID> {
}

