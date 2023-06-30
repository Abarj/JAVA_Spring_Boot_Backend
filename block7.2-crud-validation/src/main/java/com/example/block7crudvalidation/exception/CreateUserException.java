package com.example.block7crudvalidation.exception;

public class CreateUserException extends Exception{
    public CreateUserException(String mensajeError) {
        super(mensajeError);
    }
}