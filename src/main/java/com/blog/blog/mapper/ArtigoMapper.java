package com.blog.blog.mapper;


import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;

public class ArtigoMapper {

    public static ArtigoDTO mapperArtigoDTO(Artigo artigo , ArtigoDTO artigoDTO) {
        artigoDTO.setTitulo(artigo.getTitulo());
        artigoDTO.setSubtitulo(artigo.getSubtitulo());
        artigoDTO.setImagemDestacada(artigo.getImagemDestacada());
        artigoDTO.setConteudo(artigo.getConteudo());
        artigoDTO.setAutor(artigo.getAutor());
        artigoDTO.setTags(artigo.getTags());
        return artigoDTO;
    }
    public static Artigo mapArtigo(ArtigoDTO artigoDTO , Artigo artigo) {
        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setSubtitulo(artigoDTO.getSubtitulo());
        artigo.setImagemDestacada(artigoDTO.getImagemDestacada());
        artigo.setConteudo(artigoDTO.getConteudo());
        artigo.setAutor(artigoDTO.getAutor());
        artigo.setTags(artigoDTO.getTags());
        return artigo;
    }
}
