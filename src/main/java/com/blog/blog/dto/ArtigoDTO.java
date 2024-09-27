package com.blog.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ArtigoDTO {

    private Long id;

    @Size(min = 2 , message = "Informar titulo maior que 2 caracter" )
    @NotNull
    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @Size(min = 2 , message = "Informar titulo maior que 2 caracter" )
    @NotNull
    @NotBlank(message = "Subtitulo obrigatório")
    private String subtitulo;

    @Size(min = 2 , message = "Informar titulo maior que 2 caracter" )
    private String imagemDestacada;

    @Size(min = 2 , message = "Informar titulo maior que 2 caracter" )
    @NotNull
    @NotBlank(message = "Conteúdo obrigatório")
    private String conteudo;

    @Size(min = 2 , message = "Informar titulo maior que 2 caracter" )
    @NotNull
    @NotBlank(message = "Autor obrigatório")
    private String autor;

//    private Set<String> tags;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataPublicacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacaoPublicacao;
}
