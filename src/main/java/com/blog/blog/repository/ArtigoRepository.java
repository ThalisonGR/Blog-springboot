package com.blog.blog.repository;

import com.blog.blog.domain.entities.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtigoRepository extends JpaRepository<Artigo, Long> {

    Optional<Artigo> findByTituloAndAutor(String titulo, String autor);

    @Query("SELECT a from Artigo  a where  a.autor = ?1")
    Optional<List<Artigo>> findByAutor(String autor);

}
