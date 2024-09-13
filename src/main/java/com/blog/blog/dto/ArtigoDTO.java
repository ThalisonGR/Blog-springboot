package com.blog.blog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArtigoDTO {

    @NotNull
    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @NotNull
    @NotBlank(message = "Subtitulo obrigatório")
    private String subtitulo;

    private String imagemDestacada;

    @NotNull
    @NotBlank(message = "Conteúdo obrigatório")
    private String conteudo;

    @NotNull
    @NotBlank(message = "Autor obrigatório")
    private String autor;

    private Set<String> tags;
}
