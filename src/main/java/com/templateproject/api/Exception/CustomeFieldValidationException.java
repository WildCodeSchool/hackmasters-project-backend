package com.templateproject.api.Exception;

public class CustomeFieldValidationException extends Exception {

    private String fieldName;

    public CustomeFieldValidationException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}


