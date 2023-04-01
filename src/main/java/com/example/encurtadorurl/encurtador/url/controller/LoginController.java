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
    private LoginServices loginServices;

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Login loginForm) {

//        String token = loginServices.generateToken(loginForm.getEmail());

        return ResponseEntity.ok(true);
//        return ResponseEntity.ok(new JwtResponse(token));
    }

}
