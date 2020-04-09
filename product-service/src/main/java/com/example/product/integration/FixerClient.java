package com.example.product.integration;

import com.example.product.model.Fixer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name ="Fixo-ws",url = "${fixer.url}")
public interface FixerClient {

    @GetMapping("/api/latest")
    Fixer getCurrencyExchange(@RequestParam(value = "access_key") String access_key, @RequestParam(value = "format") int format) ;
}
