package com.example.encurtadorurl.encurtador.url.controller;
import com.example.encurtadorurl.encurtador.url.domain.Login;
import com.example.encurtadorurl.encurtador.url.domain.Token;
import com.example.encurtadorurl.encurtador.url.service.LoginServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@Api(value = "Login")
public class LoginController {
    @Autowired
    public LoginServices services;
    @PostMapping(path = "api/login")
    @ApiOperation(value = "Login do Serviço")
    public ResponseEntity <Optional<Token>> login(@RequestBody Login acesso){
        return ResponseEntity.status(HttpStatus.OK).body(services.login(acesso));
    }
}
