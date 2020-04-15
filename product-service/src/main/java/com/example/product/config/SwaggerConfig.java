package com.example.product.config;


import com.example.product.ProductApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.util.List;

@Configuration
@EnableSwagger2WebFlux
public class SwaggerConfig {

    @Bean
    public Docket api(ApiInfo info) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(ProductApplication.class.getPackageName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(info);
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfo(
                "Example REST API",
                "Spring Boot REST API for EXAMPLE  Task",
                "1.0",
                "put here your example terms of service ...",
                new Contact("Iqbal Masood", "", "iqbal_masood944@hotmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                List.of()
        );
    }
}
