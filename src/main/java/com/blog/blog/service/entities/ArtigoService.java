package com.blog.blog.service.entities;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.repository.ArtigoRepository;
import com.blog.blog.service.interfaces.CrudSimples;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ArtigoService implements CrudSimples<Artigo , Long , ArtigoDTO> {

    @Autowired
    private ArtigoRepository artigoRepository;

    @Override
    public Artigo save(ArtigoDTO object) {
        try {
            Artigo artigo = new Artigo(object);
            artigoRepository.save(artigo);
            log.info("Artigo salvo com sucesso");
            return artigo;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        try {
            artigoRepository.deleteById(id);
            log.info("Artigo deletado com sucesso");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Artigo update(Long id, ArtigoDTO objectDTO) {
        try {
            Artigo artigo = getById(id);
            artigo.setTitulo(objectDTO.titulo());
            artigo.setAutor(objectDTO.autor());
            artigo.setSubtitulo(objectDTO.subtitulo());
            artigo.setImagemDestacada(objectDTO.imagemDestacada());
            artigo.setConteudo(objectDTO.conteudo());
            artigoRepository.save(artigo);
            log.info("Artigo atualizado com sucesso");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Artigo getById(Long id) {
        try {
            Artigo artigo = artigoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Não existe o artigo"));
            log.info("Artigo retornado com sucesso");
            return artigo;
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public List<Artigo> getAll() {
        try {
            List<Artigo> artigo = artigoRepository.findAll();
            log.info("Artigos lista retornada com sucesso");
            return artigo;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
