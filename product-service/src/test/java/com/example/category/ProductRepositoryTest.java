package com.example.category;

import com.example.product.model.Product;
import com.example.product.model.Status;
import com.example.product.repository.ProductRepository;
import com.example.category.test.config.AbstractIntegrationTest;
import com.lordofthejars.nosqlunit.annotation.CustomComparisonStrategy;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.mongodb.MongoFlexibleComparisonStrategy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@UsingDataSet(locations = { "/datasets/products.bson" })
@CustomComparisonStrategy(comparisonStrategy = MongoFlexibleComparisonStrategy.class)
public class ProductRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private ProductRepository repository;


    @Test
    public void shouldFindAllCategories() throws Exception {
        final Flux<Product> categories = repository.findAll();

        Product productResult = new Product();
                productResult.setId(UUID.fromString("ff39c600-76d9-11ea-bc55-0242ac130003"));
                productResult.setName("Test Product");
                productResult.setDescription("some description for test product");
                productResult.setImage("http://testimage.png");
                productResult.setCategoryId("3a3f3d70-76da-11ea-bc55-0242ac130003");
                productResult.setSku("5b476600-76da-11ea-bc55-0242ac130003");
                productResult.setBrowsingName("Product1");
                productResult.setPrice(120.0);
                productResult.setListPrice(11.0);
                productResult.setQuantity(1);
                productResult.setIsStockControlled(true);
                productResult.setStatus(Status.ACTIVE);
                productResult.setRank(1);

        StepVerifier.create(repository.findAll())
                .expectNextMatches(result -> {
                    assertThat(result, is((productResult)));
                    return true;
                })
                .verifyComplete();
    }

}
