package com.jhops10.personal_finance.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Usuário não Encontrado!", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<Object> handleEmailAlreadyInUseException(EmailAlreadyInUseException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Este email já está em uso.", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<Object> handleIncorrectPasswordException(IncorrectPasswordException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Erro! Senha Incorreta", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
