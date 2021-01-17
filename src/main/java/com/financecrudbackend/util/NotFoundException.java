package com.financecrudbackend.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String entityName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with the given %s, the given value was %s",
                entityName, fieldName, fieldValue));
    }
}

