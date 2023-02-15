package com.example.encurtadorurl.encurtador.url.service;

import com.example.encurtadorurl.encurtador.url.DTO.ReciveClienteDTO;
import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import com.example.encurtadorurl.encurtador.url.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente save(ReciveClienteDTO cliente){
        Cliente user = new Cliente();
        user.setNome(cliente.getNome());
        user.setSenha(cliente.getSenha());
        user.setDateRegister(dateSave());
        return clienteRepository.save(user);
    }
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

//    public Optional<Cliente> findByName(String name) {
//        return clienteRepository.findByName(name);
//    }
    public String dateSave(){
        LocalDate dataNascimento = LocalDate.now();
        return dataNascimento.toString();
    }
    public Optional<Cliente> findById(UUID id) {
        return clienteRepository.findById(id);
    }
    public void delete(Cliente user){
        clienteRepository.delete(user);
    }
    public void deletebyid(UUID id){
        clienteRepository.deleteById(id);
    }

}
