package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import com.example.encurtadorurl.encurtador.url.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService services;
    @GetMapping(path = "api/cliente/list")
    public ResponseEntity<List<Cliente>> clientes(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/cliente/create")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.OK).body(services.save(cliente));
    }
    @GetMapping(path = "api/cliente/${id}")
    public ResponseEntity<Optional<Cliente>> findId(@RequestBody String id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
    @GetMapping(path = "api/cliente/${name}")
    public ResponseEntity<Optional<Cliente>> findName(@RequestBody String name){
        return ResponseEntity.status(HttpStatus.OK).body(services.findByName(name));
    }
    @DeleteMapping(path = "api/cliente/delete")
    public ResponseEntity delete(@RequestBody Cliente cliente){
        services.delete(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
