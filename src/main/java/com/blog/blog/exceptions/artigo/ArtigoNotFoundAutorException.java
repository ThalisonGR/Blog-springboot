package com.blog.blog.exceptions.artigo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtigoNotFoundAutorException extends RuntimeException{
    public ArtigoNotFoundAutorException(String autor){
        super(String.format("O autor %s não foi encontrado" , autor ));
    }

}
