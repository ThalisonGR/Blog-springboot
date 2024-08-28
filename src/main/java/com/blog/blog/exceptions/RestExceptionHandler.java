package com.blog.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErrorMenssage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var errorMessage = new ArrayList<String>();
        var fieldErrors = ex.getBindingResult().getFieldErrors();

        fieldErrors.forEach(fieldError -> {
            var msgError = "O campo " + fieldError.getField() + " " + fieldError.getDefaultMessage();
            errorMessage.add(msgError);
        });
        return ResponseEntity.status(status).body(new RestErrorMenssage(status.value() , errorMessage));
    }

    @ExceptionHandler(ResponseStatusException.class)
    private ResponseEntity<RestErrorMenssage> handleNotFoundException(ResponseStatusException ex){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new RestErrorMenssage(status.value() , ex.getMessage()));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    private ResponseEntity<RestErrorMenssage> headleException(HttpMessageNotReadableException ex){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new RestErrorMenssage(status.value() , "Erro ao analisar JSON: verifique a estrutura dos dados enviados"));
    }
}
