package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.Url;
import com.example.encurtadorurl.encurtador.url.repository.UrlRepository;
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
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;
    private ClienteService clienteService;


    public List<Url> findAll() {
        return urlRepository.findAll();
    }
    public Url save(String url){
        Url urlSaved = new Url();
        String hash = generateHas(url);
        urlSaved.setDateSave(dateSave());
        urlSaved.setHash(hash);
        urlSaved.setUrl(url.toString());
        urlSaved.setDateExpired(dateExpired(dateSave()));
        return urlRepository.save(urlSaved);
    }

    public LocalDate dateSave(){
        LocalDate data = LocalDate.now();
        return data;
    }
    public LocalDate dateExpired(LocalDate date){
        LocalDate diaSeguinte = date.plusDays(1);
        return diaSeguinte;
    }

    public Optional<Url> findById(UUID id) {
        return urlRepository.findById(id);
    }
    public Url getByHahs(String url) {
        return urlRepository.findByhash(url);
    }

    public String generateHas (String url){

        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        return encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString().substring(0,6);
    }
    public Url getHashUrl(String hash) {
        Url urlToRet = urlRepository.findByhash(hash);
        return urlToRet;
    }
    public Optional<Url> getUrl(String url) {
        Url urlToRet = urlRepository.findByurl(url);
        return Optional.ofNullable(urlToRet);
    }

    public void delete(Url url){
        urlRepository.delete(url);
    }
    public void deletebyid(UUID id){
        urlRepository.deleteById(id);
    }
}
