package com.blog.blog.controllers;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.exceptions.ResponseDTO;
import com.blog.blog.service.ArtigoService;
import com.blog.blog.util.ArtigoContantesRetornoStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/artigo/" , produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Arigo Controller", description = "Operations related to users")
public class  ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Operation(summary = "Criar artigo", description  = "Cria um artigo")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> created (@Valid @RequestBody ArtigoDTO artigoDTO){
            artigoService.criar_Artigo(artigoDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDTO(ArtigoContantesRetornoStatus.SALVO , ArtigoContantesRetornoStatus.STATUS_201));
    }

    @Operation(summary = "Consulta por ID", description = "Retorna um artigo que foi consultado")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @PutMapping("/update/{id}")
    public ResponseEntity<Artigo> update (@PathVariable Long id , @RequestBody ArtigoDTO artigoDTO){
            return null;
    }

    @Operation(summary = "Cosulta todos", description = "Retorna todos artigos")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @GetMapping("/getAll")
    public ResponseEntity<List<Artigo>> getAll(){
        return null;
    }

    @Operation(summary = "Consultar por ID", description = "Returns a single user")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Artigo> getByID(@PathVariable Long id){
        return null;
    }

    @Operation(summary = "Deleta artigo", description = "Deleta artigo informado na URL")
    @ApiResponse(responseCode = "200", description = "Operção realizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete (@PathVariable Long id) {
        return null;
    }
}
