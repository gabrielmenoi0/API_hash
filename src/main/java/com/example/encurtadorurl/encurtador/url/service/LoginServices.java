package com.example.encurtadorurl.encurtador.url.service;
import com.example.encurtadorurl.encurtador.url.domain.Login;
import com.example.encurtadorurl.encurtador.url.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LoginServices {
    public Optional<Token> login(Login acesso){
        Token log = new Token();
        log.setToken("dsdvsdvsdvsdvsvsdvsdvsdv");
        return Optional.of(log);
    }

}
