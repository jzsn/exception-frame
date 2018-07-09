package com.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionResponse handleSQLException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        return ExceptionResponse.create(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}
