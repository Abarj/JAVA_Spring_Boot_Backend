package com.example.block11uploaddownloadfiles1.file.exceptions;

public class FileException extends RuntimeException{
    public FileException(String e) {
        super(e);
    }
    public FileException(String e, Throwable cause) {
        super(e, cause);
    }
}
