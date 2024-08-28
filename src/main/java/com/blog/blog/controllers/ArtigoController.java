package com.blog.blog.controllers;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.service.entities.ArtigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artigo/")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @PostMapping("/save")
    public ResponseEntity<Artigo> created (@Valid @RequestBody ArtigoDTO artigoDTO){
        try {
            return new ResponseEntity<>(artigoService.save(artigoDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Artigo> update (@PathVariable Long id , @RequestBody ArtigoDTO artigoDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.update(id, artigoDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Artigo>> getAll(){
       try {
           List<Artigo> artigos = artigoService.getAll();
           return ResponseEntity.status(HttpStatus.OK).body(artigos);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Artigo> getByID(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.getById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable Long id){
        try {
            artigoService.delete(id);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
