package com.blog.blog.exceptions.artigo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ArtigonNotFoundUpdate extends RuntimeException{
    public ArtigonNotFoundUpdate(){
        super(String.format("Não foi possível atualizar o artigo" ));
    }

}
