package com.example.encurtadorurl.encurtador.url.service;

import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import com.example.encurtadorurl.encurtador.url.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente save(Cliente cliente){
        Cliente user = new Cliente();
        String hash = generateHas(cliente.getSenha());
        user.setHash(generateHas(cliente.getSenha()));
        user.setNome(cliente.getNome());
        user.setSenha(cliente.getSenha());
        return clienteRepository.save(cliente);
    }
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

//    public Optional<Cliente> findByName(String name) {
//        return clienteRepository.findByName(name);
//    }
    public Optional<Cliente> findById(String id) {
        return clienteRepository.findById(UUID.fromString(id));
    }
    public void delete(Cliente user){
        clienteRepository.delete(user);
    }
    public void deletebyid(UUID id){
        clienteRepository.deleteById(id);
    }
    public String generateHas (String senha){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        return hash.toString(32);
    }

}
