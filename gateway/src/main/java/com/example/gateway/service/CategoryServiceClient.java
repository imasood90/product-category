package com.example.gateway.service;

import com.example.gateway.model.CategoryDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@ReactiveFeignClient(name = "category-ws", url = "${remote.category}", decode404 = true)
public interface CategoryServiceClient {

    @GetMapping(path = "/api/category/{id}")
    Mono<CategoryDTO> findCategory(@PathVariable(value = "id") UUID id);
}
