package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableReactiveFeignClients
@EnableCircuitBreaker
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

//    @Bean
//    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange()
//                .pathMatchers(HttpMethod.POST, "/**").hasAuthority("SCOPE_write")
//                .pathMatchers(HttpMethod.GET, "/**").hasAuthority("SCOPE_read")
//                .pathMatchers(HttpMethod.PUT, "/**").hasAuthority("SCOPE_write")
//                .pathMatchers(HttpMethod.DELETE, "/**").hasAuthority("SCOPE_write")
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt().jwkSetUri("http://localhost:9180/.well-known/jwks.json");
//        return http.build();
//    }
}
