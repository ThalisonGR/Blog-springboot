package com.blog.blog.service.impl;

import com.blog.blog.dto.ArtigoDTO;

import java.util.List;

public interface IArtigoService {

    void criar_Artigo(ArtigoDTO artigoDTO);

    ArtigoDTO consultar_por_id(Long id);
    void atualizar_Artigo(Long id , ArtigoDTO artigoDTO);
    void excluir_Artigo(Long id);
    List<ArtigoDTO> listar_Artigo();
    List<ArtigoDTO> filtrar_autor(String autor);
}
