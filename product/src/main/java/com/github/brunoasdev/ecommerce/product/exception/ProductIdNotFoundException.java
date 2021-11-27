package com.github.brunoasdev.ecommerce.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductIdNotFoundException extends Exception {

    public ProductIdNotFoundException(String id) {
        super(String.format("Product with id %s not found in the system.", id));
    }
}
