package com.example.task.config;

import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.client.HttpClientBuilder;
import com.consol.citrus.report.MessageTracingTestListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(IntegrationTestProperties.class)
@Configuration
public class CitrusConfig {

    @Qualifier("integrationTestProperties")
    @Autowired
    private IntegrationTestProperties config;

    @Bean
    MessageTracingTestListener messageTracingListener() {
        return new MessageTracingTestListener();
    }

    @Bean
    HttpClient serviceClient() {
        return new HttpClientBuilder()
                .requestUrl(config.getServiceUrl())
                .build();
    }
}
