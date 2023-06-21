package com.example.block7CrudValidation.exceptions;

public class CreateUserException extends Exception{
    public CreateUserException(String mensajeError) {
        super(mensajeError);
    }
}