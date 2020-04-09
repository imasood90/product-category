package com.example.category;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CategoryControllerAdvice {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionMessage> handleHttpMessageNotReadableException(
            final Exception e) {
        return handleBadRequest(e);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> handleIllegalArgumentException(
            final Exception e) {
        return handleBadRequest(e);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleResourceNotFoundException(
            final Exception e) {
        return new ResponseEntity<>(new ExceptionMessage(e), HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<ExceptionMessage> handleBadRequest(final Exception e) {
        return new ResponseEntity<>(new ExceptionMessage(e), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {DuplicateKeyException.class})
    protected ResponseEntity<ExceptionMessage> handleDuplicateKeyExceptionEntityException(
            final DuplicateKeyException e) {
        return new ResponseEntity<>(new ExceptionMessage(e),
                HttpStatus.CONFLICT);
    }
}
