package com.example.encurtadorurl.encurtador.url.controller;
import com.example.encurtadorurl.encurtador.url.domain.login;
import com.example.encurtadorurl.encurtador.url.service.loginServices;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Login")
public class loginController {
    @Autowired
    private loginServices services;

    @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody login loginForm) {
            var result = services.login(loginForm);
            if(result.get().getId() == null){
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciais inválidas!");
            }else{
                return ResponseEntity.ok(result.get().getToken());
            }
    }

}
