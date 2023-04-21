package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.dto.reciveClienteDTO;
import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.repository.clienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


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
    public cliente alterCliente(cliente user) {
        repository.deleteById(user.getId());
        cliente userNew = new cliente();
        userNew.setNome(user.getNome());
        userNew.setSenha(user.getSenha());
        userNew.setEmail(user.getEmail());
        userNew.setDateRegister(user.getDateRegister());
        userNew.setToken(user.getToken());
        userNew.setId(user.getId());
        return repository.save(user);
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
        String secret="JsonWebToken";

            Map<String, Object> claims = new HashMap<>();
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(password)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 100000 * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();

        }
    }
