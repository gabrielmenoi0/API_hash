package com.example.encurtadorurl.encurtador.url.repository;
import com.example.encurtadorurl.encurtador.url.domain.url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

;
@Repository("UrlRepository")
public interface urlRepository extends JpaRepository<url, UUID> {
    public url findByhash(String hash);
    public url findByurl(String url);
}
