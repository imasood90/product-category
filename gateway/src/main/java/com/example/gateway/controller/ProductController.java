package com.example.gateway.controller;

import com.example.gateway.model.Product;
import com.example.gateway.model.ProductDTO;
import com.example.gateway.service.CategoryServiceClient;
import com.example.gateway.service.ProductServiceClient;
import com.example.gateway.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final CategoryServiceClient categoryServiceClient;
    private final ProductServiceClient productServiceClient;

    @Autowired
    public ProductController(final CategoryServiceClient categoryServiceClient, final ProductServiceClient productServiceClient) {
        this.categoryServiceClient = categoryServiceClient;
        this.productServiceClient = productServiceClient;
    }


    @PostMapping(path = "/v2")
    public Mono<ResponseEntity<ProductDTO>> saveProduct(@Valid @RequestBody final ProductDTO productDTO) {
       return categoryServiceClient.findCategory(UUID.fromString(productDTO.getCategoryId()))
                .flatMap(category -> productServiceClient.saveProduct(productDTO))
               .switchIfEmpty(Mono.error(new ResponseStatusException(
                       HttpStatus.NOT_FOUND, String.format("No category with id %s found.", productDTO.getCategoryId())
               )));

    }

    @PostMapping(path = "/")
    public Mono<ResponseEntity<ProductDTO>>saveProductv2(@Valid @RequestBody final ProductDTO productDTO) {

        return productServiceClient.saveProductv2(productDTO)
                .map(productSaved -> new ResponseEntity<>(productSaved, HttpStatus.CREATED))
                .switchIfEmpty(Mono.error(new ResponseStatusException(
                       HttpStatus.NOT_FOUND, String.format("No category with id %s found.", productDTO.getCategoryId())
               )));
    }

    @PutMapping(path = "/")
    Mono<ResponseEntity<ProductDTO>> updateProduct( @Valid @RequestBody ProductDTO productDTO) {
        return productServiceClient.updateProduct(productDTO)
                .map(productSaved -> new ResponseEntity<>(productSaved, HttpStatus.NO_CONTENT))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No product with id %s found.", productDTO.getId()))));
    }

    @GetMapping(path = "/{id}")
    Mono<ProductDTO> getProductById(@PathVariable(name = "id") UUID id, @RequestParam(name = "countrycode", required = false) String countryCode) {
        return productServiceClient.getProductById(id, countryCode)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No product with id %s found.", id))));
    }


    @GetMapping(path = "/")
    Flux<Product> getAllProducts() {
        return productServiceClient.getAllProducts()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No product found"))));
    }

    @GetMapping(path = "/category/{categoryId}")
    public Mono<List<ProductDTO>> findProductsByCategory(@PathVariable(name = "categoryId") String categoryId) {
        return categoryServiceClient.findCategory(UUID.fromString(categoryId))
                .flatMap(category -> composeApiResponse(categoryId));
    }


    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteProductById(@PathVariable(name = "id") UUID id) {
        return productServiceClient.deleteProductById(id);
    }

    @DeleteMapping(path = "/category/{categoryId}")
    Flux<Void> deleteProductsByCategoryId(@PathVariable(name = "categoryId") String categoryId){
        return productServiceClient.deleteProductsByCategoryId(categoryId);
    }

    private Mono<List<ProductDTO>> composeApiResponse(String customerId) {
        return productServiceClient.getProductsByCategory(customerId)
                .collectList()
                .flatMap(resp -> Mono.just(ObjectMapperUtils.mapAll(resp, ProductDTO.class)));

    }

}

