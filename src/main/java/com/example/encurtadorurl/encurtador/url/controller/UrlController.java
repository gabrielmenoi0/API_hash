package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.service.UrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Api(value = "Url")
public class UrlController {

    @Autowired
    private UrlService services;
    @Cacheable("url/list")
    @GetMapping(path = "api/url/list")
    @ApiOperation(value = "Listagem de Urls")
    public ResponseEntity<List<Url>> urls(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/url/create/")
    @ApiOperation(value = "Criação de urls")
    public ResponseEntity<String> create(@RequestBody String url){
        if(url.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL vazia!, insira uma URL");
        }else {
            var checkExist = services.getUrl(url.replace("\"", ""));
            if(checkExist.isEmpty() == false) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("URL Já cadastrada!");
            }else {
                var result = services.save(url.replace("\"", ""));
                if (result.getHash() != null) {
                    return ResponseEntity.status(HttpStatus.OK).body("OK");
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao encurtar a URL!");
                }
            }
        }
    }
    @Cacheable("url/id")
    @GetMapping(path = "api/url/id/{id}")
    @ApiOperation(value = "Busca URLs por ID")
    public ResponseEntity<Optional<Url>> findId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
    @Cacheable("url/hash")
    @GetMapping(path = "api/url/{hash}")
    @ApiOperation(value = "Busca e redireciona URL original pelo HASH")
    public ResponseEntity<?> findHash(@PathVariable(value = "hash")String url, HttpServletResponse response){
        Url urlToRet = services.getHashUrl(url);
        try {
            response.sendRedirect(urlToRet.getUrl());
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }
    @Cacheable("url/url")
    @GetMapping(path = "api/url/busca/{hash}")
    @ApiOperation(value = "Busca atravez do Hash retorna a URL")
    public ResponseEntity<String> findUrl(@PathVariable(value = "hash")String url){
        Url urlOptional = services.getByHahs(url);
        return ResponseEntity.status(HttpStatus.OK).body(urlOptional.getUrl());
    }

    @DeleteMapping(path = "api/url/delete")
    @ApiOperation(value = "Excluir URL")
    public ResponseEntity delete(@RequestBody Url url){
        services.delete(url);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @DeleteMapping(path = "api/url/delete/{id}")
    @ApiOperation(value = "Excluir URL por id")
    public ResponseEntity deletebyid(@PathVariable(value = "id")UUID id){
        services.deletebyid(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @DeleteMapping(path = "api/url/delete/cache")
    @ApiOperation(value = "Limpa o cache da aplicação")
    @CacheEvict("url/hash")
    public ResponseEntity deleteCache(){
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
