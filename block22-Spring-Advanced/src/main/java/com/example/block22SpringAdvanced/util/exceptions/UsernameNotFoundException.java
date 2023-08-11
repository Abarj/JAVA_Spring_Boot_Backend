package com.example.block22SpringAdvanced.util.exceptions;

public class UsernameNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "User no exist in %s"; // %s -> reemplazar

    public UsernameNotFoundException(String tableName) {
        super(String.format(ERROR_MESSAGE, tableName));
    }
}
