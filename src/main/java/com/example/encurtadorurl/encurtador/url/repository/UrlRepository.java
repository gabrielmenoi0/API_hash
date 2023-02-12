package com.example.encurtadorurl.encurtador.url.repository;
import com.example.encurtadorurl.encurtador.url.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("UrlRepository")
public interface UrlRepository extends JpaRepository<Url, UUID> {
    Optional<Url> findByPassword(String senha);
    Optional<Url> findByHash(String hash);
}
