package com.example.product.service;

import com.example.product.model.ProductDTO;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.utils.ObjectMapperUtils;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Currency;
import java.util.Locale;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final FixerService fixerService;

    private static final String DEFAULT_CURRENCY= "EUR";
    private static final String DEFAULT_COUNTRY= "EU";


    public ProductServiceImpl(ProductRepository productRepository, FixerService fixerService) {
        this.productRepository = productRepository;
        this.fixerService = fixerService;
    }

    @Override
    public Mono<ProductDTO> saveProduct(ProductDTO productDTO) {
        productDTO.setId(UUID.randomUUID());
        Product product = ObjectMapperUtils.map(productDTO, Product.class);
        System.out.println("ProductData");
        return productRepository.save(product)
                .map(productSaved -> ObjectMapperUtils.map(productSaved, ProductDTO.class));
    }

    @Override
    public Mono<ProductDTO> updateProduct(ProductDTO productDTO) {
        return productRepository.findById(productDTO.getId())
                .map(product -> ObjectMapperUtils.map(productDTO, Product.class))
                .flatMap(resp -> composeSaveProductResponse(resp))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(String.format("No product with id %s found.", productDTO.getId()))));
    }

    @Override
    public Mono<Void> deleteProductById(UUID id) {
        return productRepository.findById(id)
                .flatMap(product -> productRepository.deleteById(id));
    }

    @Override
    public Flux<Void> deleteProductByCategoryId(String id) {
        return productRepository.findProductByCategory(id)
                .flatMap(product -> productRepository.deleteById(product.getId()));


    }

    @Override
    public Mono<ProductDTO> findProductById(UUID id, String countryCode) {
        return productRepository.findById(id)
                .map(product ->
                {
                    String currencyCode = DEFAULT_CURRENCY;
                    if (!StringUtils.isEmpty(countryCode) ) {
                        currencyCode = Currency.getInstance(new Locale("en", countryCode)).getCurrencyCode();
                    }
                    if(!currencyCode.equals(DEFAULT_CURRENCY)){
                        double exchangeRateToEuro = fixerService.getExchangeRateToEuro(currencyCode);
                        product.setPrice(product.getPrice() / exchangeRateToEuro);
                    }
                    return ObjectMapperUtils.map(product, ProductDTO.class);
                })
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(String.format("No product with id %s found.", id))));
    }

    @Override
    public Flux<Product> findAll() {
        return productRepository.findAll()
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("No product found")));
    }

    @Override
    public Flux<Product> findByCategoryId(String categoryId) {
        return productRepository.findProductByCategory(categoryId)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException(String.format("No product found with the give %s categoryId",categoryId))));
    }

    private Mono<ProductDTO> composeSaveProductResponse(Product product) {
        return productRepository.save(product)
                .map(productSaved -> ObjectMapperUtils.map(productSaved, ProductDTO.class));

    }




}
