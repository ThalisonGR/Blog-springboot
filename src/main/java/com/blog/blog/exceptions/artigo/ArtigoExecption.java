package com.blog.blog.exceptions.artigo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ArtigoExecption  extends RuntimeException{
    public ArtigoExecption(String msg){
        super(msg);
    }

}
