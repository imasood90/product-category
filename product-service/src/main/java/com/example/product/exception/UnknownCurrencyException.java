package com.example.product.exception;

public class UnknownCurrencyException extends RuntimeException {
    public UnknownCurrencyException() {
        super();
    }

    public UnknownCurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownCurrencyException(String message) {
        super(message);
    }

    public UnknownCurrencyException(Throwable cause) {
        super(cause);
    }
}

