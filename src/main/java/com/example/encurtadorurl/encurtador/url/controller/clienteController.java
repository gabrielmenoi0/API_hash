package com.example.encurtadorurl.encurtador.url.controller;


import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.dto.reciveClienteDTO;
import com.example.encurtadorurl.encurtador.url.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Api(value = "Cliente")
public class clienteController {
    @Autowired
    public ClienteService services;

    @GetMapping(path = "api/cliente/list/{token}")
    @ApiOperation(value = "Listagem de clientes cadastrados")
    public ResponseEntity clientes(@PathVariable(value = "token") String token){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else{
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
        }
    }
    @PutMapping(path = "api/cliente/edit/{token}")
    @ApiOperation(value = "Edição de clientes")
    public ResponseEntity alterCliente(@PathVariable(value = "token") String token,@RequestBody cliente clienteRecive){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.alterCliente(clienteRecive));
        }
    }
    @PostMapping(path = "api/cliente/create")
    @ApiOperation(value = "Criação de clientes")
    public ResponseEntity<String> create(@RequestBody reciveClienteDTO cliente){
        var resultClient = services.findRegister(cliente.getEmail());
        if(resultClient.get().getToken() != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.save(cliente));
        }
    }
    @GetMapping(path = "api/cliente/id/{id}/{token}")
    @ApiOperation(value = "Buscar cliente por ID")
    public ResponseEntity findId(@PathVariable(value = "id")UUID id, @PathVariable(value = "token") String token){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
        }
    }

    @DeleteMapping(path = "api/cliente/delete/{token}")
    @ApiOperation(value = "Excluir cliente")
    public ResponseEntity delete(@RequestBody cliente cliente, @PathVariable(value = "token") String token){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            services.delete(cliente);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }
    @DeleteMapping(path = "api/cliente/delete/{id}/{token}")
    @ApiOperation(value = "Excluir cliente por id")
    public ResponseEntity delete(@PathVariable(value = "id")UUID id, @PathVariable(value = "token") String token){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já cadastrado!");
        }else {
            services.deletebyid(id);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }
}
