package com.example.product;

import com.example.product.model.ProductDTO;
import com.example.product.test.config.AbstractRestIntegrationTest;
import com.example.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductControllerTest extends AbstractRestIntegrationTest {

    @Mock
    private ProductService productService;

    private ProductController productController;

    @Before
    public void setup() {
        productController = new ProductController(productService);
    }


    @Test
    public void shouldSaveProduct() {

        UUID id = UUID.randomUUID();
        // given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Mobile");
        productDTO.setCategoryId("1234234234324");
        productDTO.setId(id);
        productDTO.setImage("test.png");
        productDTO.setPrice(1200.00);

        // when
        when(productService.saveProduct(productDTO)).thenReturn(Mono.just(productDTO));

        // then
        StepVerifier.create(productController.saveProduct(productDTO))
                .expectNextMatches(response -> {
                    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
                    return true;
                }).verifyComplete();
        verify(this.productService).saveProduct(ArgumentMatchers.eq(productDTO));
    }
}
