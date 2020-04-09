package com.example.product.service;

import com.example.product.exception.UnknownCurrencyException;
import com.example.product.integration.FixerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FixerService {

    private final FixerClient fixerClient;

    @Value("${fixer.api-key}")
    private String apiKey;

    private static int format = 1;

    @Autowired
    public FixerService(FixerClient fixerClient) {
        this.fixerClient = fixerClient;
    }

    public Double getExchangeRateToEuro(String currency) {
        return Optional.of(fixerClient.getCurrencyExchange(apiKey, format)
                .getRates()
                .get(currency))
                .orElseThrow(() -> new UnknownCurrencyException(String.format("Unable to find exchange rate for supplied currency %s", currency)));

    }

}
