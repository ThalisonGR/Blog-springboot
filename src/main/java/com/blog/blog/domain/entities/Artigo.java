package com.blog.blog.domain.entities;

import com.blog.blog.dto.ArtigoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "tags"})
@Table(name = "artigos")
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , length = 255)
    private String titulo;

    @Column(nullable = false , length = 255)
    private String subtitulo;

    private String imagemDestacada;

    @Column(columnDefinition = "TEXT")
    private String conteudo;

    private String autor;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_publicacao")
    private LocalDateTime dataPublicacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "data_atualizacao_publicacao")
    private LocalDateTime dataAtualizacaoPublicacao;

    @ElementCollection
    @CollectionTable(name = "artigo_tags", joinColumns = @JoinColumn(name = "artigo_id"))
    @Column(name = "tags")
    private Set<String> tags;

    public Artigo(ArtigoDTO artigoDTO) {
        this.titulo = artigoDTO.titulo();
        this.subtitulo = artigoDTO.subtitulo();
        this.imagemDestacada = artigoDTO.imagemDestacada();
        this.conteudo = artigoDTO.conteudo();
        this.autor = artigoDTO.autor();
        this.tags = artigoDTO.tags();
        this.dataPublicacao = LocalDateTime.now();
        this.dataAtualizacaoPublicacao = LocalDateTime.now();
    }

    @PrePersist
    private void criacaoArtigo() {this.dataPublicacao = LocalDateTime.now();}

    @PreUpdate
    private void atualizaDataPublicacao() {this.dataPublicacao = LocalDateTime.now();}

}
