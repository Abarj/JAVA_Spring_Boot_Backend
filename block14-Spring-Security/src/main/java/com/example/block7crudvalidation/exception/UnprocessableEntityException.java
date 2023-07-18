package com.example.block7crudvalidation.exception;

import java.util.Calendar;

public class UnprocessableEntityException extends Exception {

    public UnprocessableEntityException(String s) {
        super(s);
    }

    public CustomError UnprocessableEntityException() {
        CustomError customError = new CustomError(Calendar.getInstance().getTime(), 422, "Debe rellenar los campos correctamente");
        return customError;
    }
}