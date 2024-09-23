package com.blog.blog.exceptions;

import com.blog.blog.exceptions.artigo.ArtigoExecption;
import com.blog.blog.exceptions.artigo.ArtigoNotFoundAutorException;
import com.blog.blog.exceptions.artigo.ArtigonNotFoundUpdate;
import com.blog.blog.exceptions.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@ControllerAdvice
public class GlobalExecptionHandler {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
    public ResponseEntity<ErrorResponseDTO> headleException(HttpMessageNotReadableException ex , WebRequest request){
        var status = HttpStatus.NOT_FOUND;
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage() + "  JSON: verifique a estrutura dos dados enviados",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
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
