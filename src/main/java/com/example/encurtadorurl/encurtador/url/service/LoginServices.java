package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.Login;
import com.example.encurtadorurl.encurtador.url.domain.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServices {

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
