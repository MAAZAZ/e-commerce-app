package com.catalog.ecommerce.handler;

import com.catalog.ecommerce.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleNotFound(ItemNotFoundException ex) {
        return new ApiError(404, ex.getMessage(), System.currentTimeMillis());
    }
}