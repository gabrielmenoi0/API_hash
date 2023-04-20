package com.example.encurtadorurl.encurtador.url.repository;

import com.example.encurtadorurl.encurtador.url.domain.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("ClienteRepository")
public interface clienteRepository extends JpaRepository<cliente, UUID> {

//    public cliente findByToken(String token);

}
