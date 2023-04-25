package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.cliente;
import com.example.encurtadorurl.encurtador.url.domain.login;
import com.example.encurtadorurl.encurtador.url.repository.clienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class loginServices {

    @Autowired
    private clienteRepository repository;

    public Optional<cliente> login (login info){
        List<cliente> list = repository.findAll();
        cliente user = new cliente();
        list.forEach(element -> {
            if(element.getEmail().equals(info.getEmail()) && element.getSenha().equals(info.getPassword())){
                user.setToken(element.getToken());
                user.setId(element.getId());
                user.setNome(element.getNome());
                user.setEmail(element.getSenha());
                user.setDateRegister(element.getDateRegister());
                user.setSenha(element.getSenha());
            }
        });
        return Optional.of(user);
    }
//    private final String JWT_SECRET = "jwtsecretkey";
//
//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//
//        Date now = new Date();
//        Date expirationDate = new Date(now.getTime() + 3600000);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(now)
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
//                .compact();
//    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(JWT_SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return claims.get("username", String.class);
//    }

}
