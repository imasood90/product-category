package com.example.gateway.service;

import com.example.gateway.model.Product;
import com.example.gateway.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@ReactiveFeignClient(name = "product-ws", url = "${remote.product}", decode404 = true)
public interface ProductServiceClient {

    @PostMapping(path = "/api/product/")
    Mono<ResponseEntity<ProductDTO>> saveProduct(@Valid @RequestBody ProductDTO productDTO);

    @PostMapping(path = "/api/product/v2")
    Mono<ProductDTO> saveProductv2(@Valid @RequestBody ProductDTO productDTO);

    @PutMapping(path = "/api/product/")
    Mono<ProductDTO> updateProduct( @Valid @RequestBody ProductDTO productDTO) ;

    @GetMapping(path = "/api/product/{id}")
    Mono<ProductDTO> getProductById(@PathVariable(name = "id") UUID id, @RequestParam(name="countrycode",required = false) String countryCode);

    @DeleteMapping(path = "/{id}")
    Mono<Void> deleteProductById(@PathVariable(name = "id") UUID id);

    @GetMapping(path = "/api/product/")
    Flux<Product> getAllProducts();

    @GetMapping(path = "/api/product/category/{categoryId}")
     Flux<Product> getProductsByCategory(@PathVariable(name = "categoryId") String categoryId);

    @DeleteMapping(path = "/api/product/category/{categoryId}")
    Flux<Void> deleteProductsByCategoryId(@PathVariable(name = "categoryId") String categoryId);
//    @DeleteMapping(path = "/api/product/category/{categoryId}")
//    Flux<Void> deleteProductsByCategoryId(@PathVariable(name = "categoryId") String categoryId);
}
