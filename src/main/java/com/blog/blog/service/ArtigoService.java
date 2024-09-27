package com.blog.blog.service;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.exceptions.artigo.ArtigoExecption;
import com.blog.blog.exceptions.artigo.ArtigonNotFoundUpdate;
import com.blog.blog.repository.ArtigoRepository;
import com.blog.blog.service.impl.IArtigoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ArtigoService implements IArtigoService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ArtigoRepository artigoRepository;

    @Override
    public void criar_Artigo(ArtigoDTO artigoDTO) {
            Artigo artigo = mapper.map(artigoDTO, Artigo.class);
             Optional<Artigo> optionalArtigo =  artigoRepository.findByTituloAndAutor(artigoDTO.getTitulo(), artigoDTO.getAutor()); //retorna um boolean

             if (optionalArtigo.isPresent()){
                 throw new ArtigoExecption("Já existe um artigo com este Titulo do mesmo autor: "
                         + artigoDTO.getTitulo()
                         + " e "
                         + artigoDTO.getAutor());
             }
            artigoRepository.save(artigo);

    }

    @Override
    public ArtigoDTO consultar_por_id(Long id) {
        Optional<Artigo> optionalArtigo = artigoRepository.findById(id);
        if (optionalArtigo.isEmpty()){
            throw new ArtigoExecption("Não existe artigo com este " + id);
        }
        Artigo artigo = optionalArtigo.get();
        return mapper.map(artigo, ArtigoDTO.class);

    }

    @Override
    public ArtigoDTO atualizar_Artigo( ArtigoDTO artigoDTO) {
        Artigo artigo = artigoRepository.findById(artigoDTO.getId()).orElseThrow(
                () -> new ArtigonNotFoundUpdate());

        artigo.setTitulo(artigoDTO.getTitulo());
        artigo.setSubtitulo(artigoDTO.getSubtitulo());
        artigo.setImagemDestacada(artigoDTO.getImagemDestacada());
        artigo.setConteudo(artigo.getConteudo());
        artigo.setAutor(artigoDTO.getAutor());

        artigoRepository.save(artigo);
        return mapper.map(artigo , ArtigoDTO.class);
    }


    @Override
    public void excluir_Artigo(Long id) {
        try {
            ArtigoDTO artigoDTO = consultar_por_id(id);
            artigoRepository.deleteById(id);
        }catch (Exception e){
            throw new ArtigoExecption("Não foi possível excluir o artigo id "
                    + id
                    + " / "
                    + e.getMessage());
        }
    }

    @Override
    public List<ArtigoDTO> listar_Artigo() {
        List<Artigo> artigos = artigoRepository.findAll();
        if (artigos.isEmpty()){
            throw new ArtigoExecption("Problema ao carregar lista de artigo");
        }
       return artigos.stream()
               .map((artigo) -> mapper.map(artigo, ArtigoDTO.class))
               .collect(Collectors.toList());
    }

    @Override
    public List <ArtigoDTO> filtrar_autor(String autor) {
        return null;

    }
}
