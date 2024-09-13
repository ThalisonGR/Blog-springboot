package com.blog.blog.service.impl;

import com.blog.blog.dto.ArtigoDTO;

import java.util.List;

public interface IArtigoService {

    void criar_Artigo(ArtigoDTO artigoDTO);
    void atualizar_Artigo(Long id , ArtigoDTO artigoDTO);
    void excluir_Artigo(Long id);
    List<ArtigoDTO> listar_Artigo();
}
