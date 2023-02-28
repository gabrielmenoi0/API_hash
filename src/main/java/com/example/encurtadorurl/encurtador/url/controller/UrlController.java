package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.service.UrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "api/url/list")
    @ApiOperation(value = "Listagem de Urls")
    public ResponseEntity<List<Url>> urls(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/url/create/")
    @ApiOperation(value = "Criação de urls")
    public ResponseEntity<Url> create(@RequestBody String url){
        return ResponseEntity.status(HttpStatus.OK).body(services.save(url));
    }

    @GetMapping(path = "api/url/id/{id}")
    @ApiOperation(value = "Buscar Senhas por ID")
    public ResponseEntity<Optional<Url>> findId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
    @GetMapping(path = "api/url/{hash}")
    @ApiOperation(value = "Busca e redireciona URL original pelo HASH")
    public ResponseEntity<?> finHash(@PathVariable(value = "hash")String url, HttpServletResponse response){
        Url urlToRet = services.getHashUrl(url);

        try {
            response.sendRedirect(urlToRet.getUrl());
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping(path = "api/url/busca")
    @ApiOperation(value = "Busca e redireciona URL original pelo URL")
    public ResponseEntity<?> findUrl(@RequestBody String url, HttpServletResponse response){
        Url urlToRet = services.getUrl(url);

        try {
            response.sendRedirect(urlToRet.getUrl());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
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
}
