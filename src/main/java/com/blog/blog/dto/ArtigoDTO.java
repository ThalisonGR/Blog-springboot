package com.blog.blog.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

public record ArtigoDTO(

                        @NotNull
                        @NotBlank(message = "Título obrigatório")
                        String titulo,

                        @NotNull
                        @NotBlank(message = "Subtitulo obrigatório")
                        String subtitulo ,

                        String imagemDestacada,

                        @NotNull
                        @NotBlank(message = "Conteúdo obrigatório")
                        String conteudo,

                        @NotNull
                        @NotBlank(message = "Autor obrigatório")
                        String autor,

                        Set<String> tags,

                        LocalDateTime dataPublicacao,
                        LocalDateTime dataAtualizacao) {
}
