package com.blog.blog.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record ArtigoDTO(String titulo,
                        String subtitulo ,
                        String imagemDestacada,
                        String conteudo,
                        String autor,
                        Set<String> tags,
                        LocalDateTime dataPublicacao,
                        LocalDateTime dataAtualizacao) {
}
