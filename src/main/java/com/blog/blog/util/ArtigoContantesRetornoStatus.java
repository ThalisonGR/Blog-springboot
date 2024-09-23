package com.blog.blog.util;

import lombok.Data;

@Data
public class ArtigoContantesRetornoStatus {

    private ArtigoContantesRetornoStatus() {

    }

    public static final String SALVO = "SALVO COM SUCESSO";
    public static final String STATUS_201 = "201";
    public static final String STATUS_200 = "200";
    public static final String CREATE = "CRIADO ARTIGO COM SUCESSO";
    public static final String EXCLUSAO = "ARTIGO EXCLUÍDO COM SUCESSOS";

}