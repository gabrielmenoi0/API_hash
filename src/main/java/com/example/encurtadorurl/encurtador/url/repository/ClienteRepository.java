package com.example.encurtadorurl.encurtador.url.repository;

import com.example.encurtadorurl.encurtador.url.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("ClienteRepository")
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Optional<Cliente> findByName(String ClienteName);

}
