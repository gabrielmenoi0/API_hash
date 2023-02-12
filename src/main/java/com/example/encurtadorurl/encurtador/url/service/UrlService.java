package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    private ClienteService clienteService;


    public List<Url> findAll() {
        return urlRepository.findAll();
    }
    public Url save(Url senha){
        Url password = new Url();
        String hash = clienteService.generateHas(senha.getSenha());
        password.setDateSave(new Date(System.currentTimeMillis()));
        password.setHash(hash);
        password.setSenha(senha.getSenha());
        return urlRepository.save(password);
    }

    public Optional<Url> findById(String id) {
        return urlRepository.findById(UUID.fromString(id));
    }

    public Optional<Url> findByPassword(String password) {
        return urlRepository.findByPassword(password);
    }
    public Optional<Url> findByHash(String hash) {
        return urlRepository.findByHash(hash);
    }
    public void delete(Url url){
        urlRepository.delete(url);
    }
}
