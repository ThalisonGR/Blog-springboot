package com.blog.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArtigoDTO {

    private Long id;

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

//    private Set<String> tags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPublicacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacaoPublicacao;
}
