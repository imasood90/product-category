package com.hse24.task;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hse24.task.config.base.AbstractIT;
import com.hse24.task.model.ProductDTO;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.UUID;

public class AddProduct extends AbstractIT {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    @CitrusTest
    public void shouldAddNewProduct(@CitrusResource TestContext context)
            throws Exception {
        // test case meta data
        author("Iqbal Masood");

        description("Should add new product request");

        variable("productIdValue", "");

        //UUID productId = UUID.randomUUID();

        ProductDTO productDTO =new ProductDTO();
        productDTO.setName("Mobile");
        productDTO.setCategoryId("1234234234324");
        productDTO.setImage("test.png");
        productDTO.setPrice(1200.00);

        postRequest(PRODUCT_URL,
                mapper.writeValueAsString(productDTO));

        verifyResponse(HttpStatus.CREATED, "id");

        String id = context.getVariable("${productIdValue}");



        getRequest(PRODUCT_URL +id);

        productDTO.setId(UUID.fromString(id));

        verifyResponsePayload(HttpStatus.OK,
                mapper.writeValueAsString(productDTO));
    }
}
