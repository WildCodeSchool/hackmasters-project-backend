package com.templateproject.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such entity")
public class MissingEntityException extends Exception {

    public MissingEntityException(String message) {
        super(message);
    }
}
