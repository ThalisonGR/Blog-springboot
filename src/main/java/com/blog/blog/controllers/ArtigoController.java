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
            return new ResponseEntity<>(artigoService.save(artigoDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Artigo> update (@PathVariable Long id , @RequestBody ArtigoDTO artigoDTO){
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.update(id, artigoDTO));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Artigo>> getAll(){
           List<Artigo> artigos = artigoService.getAll();
           return ResponseEntity.status(HttpStatus.OK).body(artigos);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Artigo> getByID(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable Long id) {
        artigoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}
