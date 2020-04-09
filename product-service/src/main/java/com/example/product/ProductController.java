package com.example.product;

import com.example.product.model.ProductDTO;
import com.example.product.model.Product;
import com.example.product.service.ProductService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(path = "/{id}")
    public Mono<ProductDTO> getProductById(@PathVariable(name = "id") UUID id, @RequestParam(name="countrycode",required = false) String countryCode) {
        return productService.findProductById(id, countryCode);
    }

    @GetMapping(path = "/")
    public Flux<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping(path = "/")
    public Mono<ResponseEntity<ProductDTO>> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO)
                .map(productSaved -> new ResponseEntity<>(productSaved, HttpStatus.CREATED));
    }

    @PostMapping(path = "/v2")
    public Mono<ProductDTO> savev2Product(@Valid @RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
                //.map(productSaved -> new ResponseEntity<>(productSaved, HttpStatus.CREATED));
    }

    @PutMapping(path = "/")
    public Mono<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }


    @DeleteMapping(path = "/{id}")
    public Mono<Void> deleteProductById(@PathVariable(name = "id") UUID id) {
        return productService.deleteProductById(id);
    }

    @DeleteMapping(path = "/category/{categoryId}")
    public Flux<Void> deleteProductByCategoryId(@PathVariable(name = "categoryId") String id) {
        return productService.deleteProductByCategoryId(id);
    }

    @GetMapping(path = "/category/{categoryId}")
    public Flux<Product> getProductsByCategory(@PathVariable(name = "categoryId") String categoryId) {
        return productService.findByCategoryId(categoryId);
    }
}
