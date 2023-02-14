package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.service.UrlService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController

public class UrlController {

    @Autowired
    private UrlService services;

    @GetMapping(path = "api/url/list")
    @ApiOperation(value = "Listagem de Senhas")
    public ResponseEntity<List<Url>> urls(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/url/create")
    @ApiOperation(value = "Criação de senhas")
    public ResponseEntity<Url> create(@RequestBody Url url){
        return ResponseEntity.status(HttpStatus.OK).body(services.save(url));
    }

    @GetMapping(path = "api/url/id")
    @ApiOperation(value = "Buscar Senhas por ID")
    public ResponseEntity<Optional<Url>> findId(@RequestParam UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
//    @GetMapping(path = "api/url/hash/${hash}")
//    @ApiOperation(value = "Buscar senhas por HASH")
//    public ResponseEntity<Optional<Url>> findHahs(@RequestBody String hahs){
//        return ResponseEntity.status(HttpStatus.OK).body(services.findByHash(hahs));
//    }
//    @GetMapping(path = "api/url/password/${password}")
//    @ApiOperation(value = "Buscar senhas")
//    public ResponseEntity<Optional<Url>> findPassword(@RequestBody String pasword){
//        return ResponseEntity.status(HttpStatus.OK).body(services.findByPassword(pasword));
//    }
    @DeleteMapping(path = "api/url/delete")
    @ApiOperation(value = "Excluir senha")
    public ResponseEntity delete(@RequestBody Url url){
        services.delete(url);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @DeleteMapping(path = "api/url/delete/{id}")
    @ApiOperation(value = "Excluir senha por id")
    public ResponseEntity deletebyid(@PathVariable(value = "id")UUID id){
        services.deletebyid(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
