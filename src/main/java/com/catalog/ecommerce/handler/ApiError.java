package com.catalog.ecommerce.handler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private long timestamp;
}
