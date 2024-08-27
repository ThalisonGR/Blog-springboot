package com.blog.blog.service.interfaces;

public interface CrudSimples <T,S, R> {
    T save (R object);
    void  delete(S id);
    T update(Long id , R Object);
    T getById(S id);
}

