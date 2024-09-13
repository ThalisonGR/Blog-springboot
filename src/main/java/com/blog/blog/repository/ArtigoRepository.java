package com.blog.blog.repository;

import com.blog.blog.domain.entities.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    Optional<Artigo> findByTituloAndAutor(String titulo, String autor);
}
