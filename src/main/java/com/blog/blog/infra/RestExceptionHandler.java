package com.blog.blog.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

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

    private ResponseEntity<RestErrorMenssage> headleException(ResponseStatusException ex){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(new RestErrorMenssage(status.value() , "Informe ao backend"));
    }
}
