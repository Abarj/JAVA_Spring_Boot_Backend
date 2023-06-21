package org.example;

public class InvalidLineFormatException extends Exception {
    // Creamos una función que nos muestre la excepción
    // Esta función toma como parámetro un mensaje (String)
    public InvalidLineFormatException(String message) {
        // Devuelve el mensaje de error
        super(message);
    }
}
