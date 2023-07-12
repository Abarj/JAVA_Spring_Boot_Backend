package com.example.block7crudvalidation.exception;

import java.util.Calendar;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String s) {
        super(s);
    }

    public CustomError EntityNotFoundException(String errorMessage) {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 404, "No se han podido encontrar resultados");
        return customError;
    }
}