package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.domain.url;
import com.example.encurtadorurl.encurtador.url.repository.urlRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class urlService {
    @Autowired
    private urlRepository repositoryUrl;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    CacheManager cacheManager;


    public List<url> findAll() {
        return repositoryUrl.findAll();
    }
    public List<url> findByToken(String token) {
        List<url> list = repositoryUrl.findAll();
        List<url> urlList = new ArrayList<url>();
        list.forEach(element -> {
            if(element.getUser().equals(token)){
                urlList.add(element);
            }
        });
        return urlList;
    }
    public url save(String url, cliente user){
        url urlSaved = new url();
        String hash = generateHas(url);
        urlSaved.setDateSave(dateSave());
        urlSaved.setHash(hash);
        urlSaved.setUrl(url.toString());
        urlSaved.setDateExpired(dateExpired(dateSave()));
        urlSaved.setUser(user.getId().toString());
        return repositoryUrl.save(urlSaved);
    }

    public LocalDate dateSave(){
        LocalDate data = LocalDate.now();
        return data;
    }
    public void deleteCache(String cacheName){
//        evictSingleCacheValue(cacheName,cacheKey);
        evictAllCacheValues(cacheName);
    }

//    public void evictSingleCacheValue(String cacheName, String cacheKey) {
//        cacheManager.getCache(cacheName).evict(cacheKey);
//    }

    public void evictAllCacheValues(String cacheName) {
        cacheManager.getCache(cacheName).clear();
    }
    public LocalDate dateExpired(LocalDate date){
        LocalDate diaSeguinte = date.plusDays(1);
        return diaSeguinte;
    }

    public Optional<url> findById(UUID id) {
        return repositoryUrl.findById(id);
    }
    public url getByHash(String url) {
        return repositoryUrl.findByhash(url);
    }

    public String generateHas (String url){

        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        return encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString().substring(0,6);
    }
    public url getHashUrl(String hash) {
        url urlToRet = repositoryUrl.findByhash(hash);
        return urlToRet;
    }
    public List<url> getUrl(String url) {
        List<url> list = repositoryUrl.findAll();
        List<url> urlList = new ArrayList<url>();
        list.forEach(element -> {
            if(element.getUrl().equals(url)){
                urlList.add(element);
            }
        });

//       url urlToRet = repositoryUrl.findByurl(url);
        return urlList;
    }

    public void delete(url url){
        repositoryUrl.delete(url);
    }
    public void deletebyid(UUID id){
        repositoryUrl.deleteById(id);
    }
}
