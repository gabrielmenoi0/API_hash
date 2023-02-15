package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import com.example.encurtadorurl.encurtador.url.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClienteController {
    @Autowired
    public ClienteService services;

    @GetMapping(path = "api/cliente/list")
    @ApiOperation(value = "Listagem de clientes cadastrados")
    public ResponseEntity<List<Cliente>> clientes(){
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
    }
    @PostMapping(path = "api/cliente/create")
    @ApiOperation(value = "Criação de clientes")
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.OK).body(services.save(cliente));
    }
    @GetMapping(path = "api/cliente/id/{id}")
    @ApiOperation(value = "Buscar cliente por ID")
    public ResponseEntity<Optional<Cliente>> findId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
    }
//    @GetMapping(path = "api/cliente/${name}")
//    @ApiOperation(value = "Buscar cliente por nome")
//    public ResponseEntity<Optional<Cliente>> findName(@RequestBody String name){
//        return ResponseEntity.status(HttpStatus.OK).body(services.findByName(name));
//    }
    @DeleteMapping(path = "api/cliente/delete")
    @ApiOperation(value = "Excluir cliente")
    public ResponseEntity delete(@RequestBody Cliente cliente){
        services.delete(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
    @DeleteMapping(path = "api/cliente/delete/{id}")
    @ApiOperation(value = "Excluir cliente por id")
    public ResponseEntity delete(@PathVariable(value = "id")UUID id){
        services.deletebyid(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
