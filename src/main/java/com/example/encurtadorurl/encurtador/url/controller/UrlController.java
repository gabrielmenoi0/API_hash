package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UrlController {

    @Autowired
    private UrlService services;

    @GetMapping(path = "api/url/list")
    public ResponseEntity<List<Url>> urls(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/url/create")
    public ResponseEntity<Url> create(@RequestBody Url url){
        return ResponseEntity.status(HttpStatus.OK).body(services.save(url));
    }

    @GetMapping(path = "api/url/${id}")
    public ResponseEntity<Optional<Url>> findId(@RequestBody String id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
    @GetMapping(path = "api/url/hash/${hash}")
    public ResponseEntity<Optional<Url>> findHahs(@RequestBody String hahs){
        return ResponseEntity.status(HttpStatus.OK).body(services.findByHash(hahs));
    }
    @GetMapping(path = "api/url/password/${password}")
    public ResponseEntity<Optional<Url>> findPassword(@RequestBody String pasword){
        return ResponseEntity.status(HttpStatus.OK).body(services.findByPassword(pasword));
    }
    @DeleteMapping(path = "api/url/delete")
    public ResponseEntity delete(@RequestBody Url url){
        services.delete(url);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
