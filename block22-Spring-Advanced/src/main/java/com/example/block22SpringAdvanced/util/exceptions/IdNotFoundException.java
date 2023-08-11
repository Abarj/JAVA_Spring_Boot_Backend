package com.example.block22SpringAdvanced.util.exceptions;

public class IdNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Record no exist in %s"; // %s -> reemplazar

    public IdNotFoundException(String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
