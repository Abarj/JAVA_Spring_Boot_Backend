package com.example.block22SpringAdvanced.util.exceptions;

public class ForbiddenCustomerException extends RuntimeException {

    public ForbiddenCustomerException() {
        super("This client is banned");
    }
}
