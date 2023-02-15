package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
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
    public Url save(String url){
        Url password = new Url();
        String hash = generateHas(url);
        password.setDateSave(dateSave());
        password.setHash(hash);
        password.setUrl(url.toString());
        return urlRepository.save(password);
    }

    public String dateSave(){
        LocalDate dataNascimento = LocalDate.now();
        return dataNascimento.toString();
    }

    public Optional<Url> findById(UUID id) {
        return urlRepository.findById(id);
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
//    public Optional<Url> findByPassword(String password) {
//        return urlRepository.findByPassword(password);
//    }
    public Optional<Url> findByHash(String hash) {
        return urlRepository.findByHash(hash);
    }
    public void delete(Url url){
        urlRepository.delete(url);
    }
    public void deletebyid(UUID id){
        urlRepository.deleteById(id);
    }
}
