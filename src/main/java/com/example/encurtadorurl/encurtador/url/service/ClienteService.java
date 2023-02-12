package com.example.encurtadorurl.encurtador.url.service;

import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import com.example.encurtadorurl.encurtador.url.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.setHash(hash);
        user.setNome(cliente.getNome());
        return clienteRepository.save(cliente);
    }
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findByName(String name) {
        return clienteRepository.findByName(name);
    }
    public Optional<Cliente> findById(String id) {
        return clienteRepository.findById(UUID.fromString(id));
    }
    public void delete(Cliente user){
        clienteRepository.delete(user);
    }
    public String generateHas (String senha){
        return "";
    }

}
