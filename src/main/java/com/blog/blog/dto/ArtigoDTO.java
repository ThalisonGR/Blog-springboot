package com.blog.blog.dto;

import java.time.LocalDateTime;

public record ArtigoDTO(String titulo,
                        String subtitulo ,
                        String imagemDestacada,
                        String conteudo,
                        String autor,
                        LocalDateTime dataPublicacao,
                        LocalDateTime dataAtualizacao) {
}
