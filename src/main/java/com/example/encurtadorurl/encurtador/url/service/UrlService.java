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

    public String generateHas (String url){

        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }
    public Url getHashUrl(String hahs) {
        Url urlToRet = urlRepository.findByhash(hahs);
        return urlToRet;
    }
    public Url getUrl(String url) {
        Url urlToRet = urlRepository.findByurl(url);
        return urlToRet;
    }

    public void delete(Url url){
        urlRepository.delete(url);
    }
    public void deletebyid(UUID id){
        urlRepository.deleteById(id);
    }
}
