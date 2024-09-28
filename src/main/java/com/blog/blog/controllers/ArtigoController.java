package com.blog.blog.controllers;

import com.blog.blog.domain.entities.Artigo;
import com.blog.blog.dto.ArtigoDTO;
import com.blog.blog.exceptions.dto.ResponseDTO;
import com.blog.blog.service.ArtigoService;
import com.blog.blog.util.ArtigoContantesRetornoStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/artigo/" , produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
        name = "CRUD REST API para Blog",
        description = "CREATE,UPDATE,READ,DELETE")
@Validated
public class  ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @Operation(
            summary = "Criar artigo",
            description  = "Cria um artigo")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> created (@Valid @RequestBody ArtigoDTO artigoDTO){
            artigoService.criar_Artigo(artigoDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDTO(ArtigoContantesRetornoStatus.SALVO , ArtigoContantesRetornoStatus.STATUS_201));
    }
    @Operation(
            summary = "Consulta por ID",
            description = "Retorna um artigo que foi consultado")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @GetMapping("/cusulta/{autor}")
    public ResponseEntity<ArtigoDTO> consultar_autor (@RequestParam String autor){
        return null;
    }


    @Operation(
            summary = "Consulta por ID",
            description = "Retorna um artigo que foi consultado")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @PutMapping("/update/{id}")
    public ResponseEntity<ArtigoDTO> update (@PathVariable Long id ,@Valid @RequestBody ArtigoDTO artigoDTO){
        artigoDTO.setId(id);
        ArtigoDTO updateArtigo = artigoService.atualizar_Artigo(artigoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateArtigo);
    }

    @Operation(
            summary = "Cosulta todos",
            description = "Retorna todos artigos")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @GetMapping("/getAll")
    public ResponseEntity<List<ArtigoDTO>> getAll(){
        List<ArtigoDTO> artigoDTOS = artigoService.listar_Artigo();
        return ResponseEntity.ok().body(artigoDTOS);
    }

    @Operation(
            summary = "Consultar por ID",
            description = "Returns a single user")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @GetMapping("/consultar_id/{id}")
    public ResponseEntity<ArtigoDTO> consultar_id(@PathVariable Long id){
        ArtigoDTO artigoDTO = artigoService.consultar_por_id(id);
        return  ResponseEntity.ok(artigoDTO);
    }

    @Operation(
            summary = "Deleta artigo",
            description = "Deleta artigo informado na URL")
    @ApiResponse(
            responseCode = "200",
            description = "Operção realizada com sucesso")
    @ApiResponse(
            responseCode = "404",
            description = "Artigo not found")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete (@PathVariable Long id) {
        artigoService.excluir_Artigo(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ArtigoContantesRetornoStatus.EXCLUSAO, ArtigoContantesRetornoStatus.STATUS_200));
    }
}
