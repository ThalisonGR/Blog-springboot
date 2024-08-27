package com.blog.blog.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artigos")
public class Artigos {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false , length = 255)
    @NotBlank(message = "Título obrigatório")
    private String titulo;

    @Column(nullable = false , length = 255)
    @NotBlank(message = "Subtitulo obrigatório")
    private String subtitulo;

    private String imagemDestacada;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Subtitulo obrigatório")
    private String conteudo;

    @NotBlank(message = "Autor obrigatório")
    private String autor;

    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    @Column(name = "data_atualizacao_publicacao")
    private LocalDateTime dataAtualizacaoPublicacao;

    @ElementCollection
    @CollectionTable(name = "artigo_tags", joinColumns = @JoinColumn(name = "artigo_id"))
    @Column(name = "tags")
    private Set<String> tags;

    @PrePersist
    private void criacaoArtigo() {this.dataPublicacao = LocalDateTime.now();}

    @PreUpdate
    private void atualizaDataPublicacao() {this.dataPublicacao = LocalDateTime.now();}

}
