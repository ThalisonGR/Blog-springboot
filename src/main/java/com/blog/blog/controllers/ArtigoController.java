package com.blog.blog.controllers;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.service.entities.ArtigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/artigo/")
@Tag(name = "Arigo Controller", description = "Operations related to users")
public class  ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Operation(summary = "Criar artigo", description  = "Cria um artigo")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @PostMapping("/save")
    public ResponseEntity<Artigo> created (@Valid @RequestBody ArtigoDTO artigoDTO){
            return new ResponseEntity<>(artigoService.save(artigoDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Consulta por ID", description = "Retorna um artigo que foi consultado")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @PutMapping("/update/{id}")
    public ResponseEntity<Artigo> update (@PathVariable Long id , @RequestBody ArtigoDTO artigoDTO){
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.update(id, artigoDTO));
    }

    @Operation(summary = "Cosulta todos", description = "Retorna todos artigos")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @GetMapping("/getAll")
    public ResponseEntity<List<Artigo>> getAll(){
           List<Artigo> artigos = artigoService.getAll();
           return ResponseEntity.status(HttpStatus.OK).body(artigos);
    }

    @Operation(summary = "Consultar por ID", description = "Returns a single user")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Artigo> getByID(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(artigoService.getById(id));
    }

    @Operation(summary = "Deleta artigo", description = "Deleta artigo informado na URL")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable Long id) {
        artigoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
    }
}
