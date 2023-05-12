package com.example.encurtadorurl.encurtador.url.controller;

import com.example.encurtadorurl.encurtador.url.MyRebbit.MyRabbitMQSender;
import com.example.encurtadorurl.encurtador.url.dto.MessegeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RebbitMQController {
    @Autowired
    private MyRabbitMQSender rabbitMQSender;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) {
        rabbitMQSender.send(message);
    }
}
