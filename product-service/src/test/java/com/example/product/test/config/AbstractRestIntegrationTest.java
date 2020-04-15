package com.example.product.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.web.context.WebApplicationContext;


@AutoConfigureWebTestClient
public abstract class AbstractRestIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected WebApplicationContext wac;


}
