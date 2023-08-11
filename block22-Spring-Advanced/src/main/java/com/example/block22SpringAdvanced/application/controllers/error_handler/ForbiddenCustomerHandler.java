package com.example.block22SpringAdvanced.application.controllers.error_handler;

import com.example.block22SpringAdvanced.application.models.responses.BaseErrorResponse;
import com.example.block22SpringAdvanced.application.models.responses.ErrorResponse;
import com.example.block22SpringAdvanced.util.exceptions.ForbiddenCustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenCustomerHandler {

    @ExceptionHandler(ForbiddenCustomerException.class)
    public BaseErrorResponse handleIdNotFound(ForbiddenCustomerException exception) {
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.FORBIDDEN.name())
                .code(HttpStatus.FORBIDDEN.value())
                .build();
    }
}
