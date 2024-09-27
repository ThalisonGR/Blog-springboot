package com.blog.blog.exceptions;

import com.blog.blog.exceptions.artigo.ArtigoExecption;
import com.blog.blog.exceptions.artigo.ArtigoNotFoundAutorException;
import com.blog.blog.exceptions.artigo.ArtigonNotFoundUpdate;
import com.blog.blog.exceptions.dto.ErrorResponseDTO;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExecptionHandler extends  ResponseEntityExceptionHandler{

    /*
        Interessante saber para quando ocorrer "Error creating bean with name 'handlerExceptionResolver' defined in class path resource"
        https://stackoverflow.com/questions/51991992/getting-ambiguous-exceptionhandler-method-mapped-for-methodargumentnotvalidexce/74552716#74552716
        Remova @ExceptionHandleranotações dos métodos que manipulam as mesmas exceções como em ResponseEntityExceptionHandler
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String , String> validacaoError = new HashMap<>();
        List<ObjectError> validacaoErrorLista = ex.getBindingResult().getAllErrors();

        validacaoErrorLista.forEach((error) ->{
            String nome_do_campo = ((FieldError)error).getField();
            String validacao_mensagem = error.getDefaultMessage();
            validacaoError.put(nome_do_campo, validacao_mensagem);
        });
        return new ResponseEntity<>(validacaoError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> hadleGlobalExcpetion(Exception exception , WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ArtigoExecption.class)
    public ResponseEntity<ErrorResponseDTO> hadleArtigoPublicadoaAnteriomente(ArtigoExecption exeception , WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false), // Se informar true irá apresentar as informaç~eos do cliente
                HttpStatus.BAD_REQUEST,
                exeception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArtigoNotFoundAutorException.class)
    public ResponseEntity<ErrorResponseDTO> hadleAutorNaoEncontrado(ArtigoNotFoundAutorException exception , WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArtigonNotFoundUpdate.class)
    public ResponseEntity<ErrorResponseDTO> hadleUpdateArtigo(ArtigonNotFoundUpdate exception , WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
    }




}
