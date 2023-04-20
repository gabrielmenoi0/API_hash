package com.example.encurtadorurl.encurtador.url.controller;
import com.example.encurtadorurl.encurtador.url.dto.reciveClienteDTO;
import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
        }else{
        return ResponseEntity.status(HttpStatus.OK).body(services.findAll());
        }
    }
//    @PutMapping(path = "api/cliente/")
//    @ApiOperation(value = "Edição de clientes")
//    public ResponseEntity<List<Cliente>> alterCliente(@RequestBody Cliente cliente){
//        return ResponseEntity.status(HttpStatus.OK).body(services.alterCliente(cliente));
//    }
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(services.findById(id));
        }
    }
//    @GetMapping(path = "api/cliente/${name}")
//    @ApiOperation(value = "Buscar cliente por nome")
//    public ResponseEntity<Optional<Cliente>> findName(@RequestBody String name){
//        return ResponseEntity.status(HttpStatus.OK).body(services.findByName(name));
//    }
    @DeleteMapping(path = "api/cliente/delete/{token}")
    @ApiOperation(value = "Excluir cliente")
    public ResponseEntity delete(@RequestBody cliente cliente, @PathVariable(value = "token") String token){
        var resultClient = services.findByToken(token);
        if(resultClient.get().getToken() ==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado!");
        }else {
            services.deletebyid(id);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
    }
}
