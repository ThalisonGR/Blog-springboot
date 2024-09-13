package com.blog.blog.service;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.exceptions.ArtigoExecption;
import com.blog.blog.mapper.ArtigoMapper;
import com.blog.blog.repository.ArtigoRepository;
import com.blog.blog.service.impl.IArtigoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ArtigoService implements IArtigoService {

    @Autowired
    private ArtigoRepository artRepository;

    @Override
    public void criar_Artigo(ArtigoDTO artigoDTO) {
            Artigo artigo = ArtigoMapper.mapArtigo(artigoDTO , new Artigo()); //Essa linha todos os dados que tiver dentro do artigo DTO serão tranferidos para o objeto artigo
             Optional<Artigo> optionalArtigo =  artigoRepository.findByTituloAndAutor(artigoDTO.getTitulo(), artigoDTO.getAutor()); //retorna um boolean

             if (optionalArtigo.isPresent()){
                 throw new ArtigoExecption("Já existe um art1igo com este Titulo do mesmo autor: "
                         + artigoDTO.getTitulo()
                         + " "
                         + artigoDTO.getAutor());
             }
            artRepository.save(artigo);

    }

    @Override
    public void atualizar_Artigo(Long id, ArtigoDTO artigoDTO) {

    }

    @Override
    public void excluir_Artigo(Long id) {

    }

    @Override
    public List<ArtigoDTO> listar_Artigo() {
        return List.of();
    }

    @Autowired
    private ArtigoRepository artigoRepository;

}
