package com.example.encurtadorurl.encurtador.url.service;

import com.example.encurtadorurl.encurtador.url.dto.reciveClienteDTO;
import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.repository.clienteRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private clienteRepository repository;
    public String save(reciveClienteDTO clienteRecive){
        cliente user = new cliente();
        user.setNome(clienteRecive.getNome());
        user.setSenha(clienteRecive.getSenha());
        user.setEmail(clienteRecive.getEmail());
        user.setDateRegister(dateSave());
        user.setToken(generateToken(clienteRecive.getSenha()));
        var result = repository.save(user);
        return result.getToken();
    }
    public List<cliente> findAll() {
        return repository.findAll();
    }

//    public Optional<Cliente> findByName(String name) {
//        return clienteRepository.findByName(name);
//    }
    public String dateSave(){
        LocalDate dataNascimento = LocalDate.now();
        return dataNascimento.toString();
    }
    public Optional<cliente> findById(UUID id) {
        return repository.findById(id);
    }
    public Optional<cliente> findByToken(String token) {
        List<cliente> list = repository.findAll();
        cliente user = new cliente();
        list.forEach(element -> {
            if(element.getToken().toString().equals(token)){
                user.setToken(element.getToken());
                user.setId(element.getId());
                user.setNome(element.getNome());
                user.setEmail(element.getSenha());
                user.setDateRegister(element.getDateRegister());
                user.setSenha(element.getSenha());
            }
        });
        return Optional.of(user);
    }
    public Optional<cliente> findRegister(String email) {
        List<cliente> list = repository.findAll();
        cliente user = new cliente();
        list.forEach(element -> {
            if(element.getEmail().equals(email)){
                user.setToken(element.getToken());
                user.setId(element.getId());
                user.setNome(element.getNome());
                user.setEmail(element.getSenha());
                user.setDateRegister(element.getDateRegister());
                user.setSenha(element.getSenha());
            }
        });
        return Optional.of(user);
    }
    public void delete(cliente user){
        repository.delete(user);
    }
    public void deletebyid(UUID id){
        repository.deleteById(id);
    }
    public String generateToken(String password){
        LocalDateTime time = LocalDateTime.now();
        return Hashing.murmur3_32()
                .hashString(password.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
    }

}
