package com.example.springwebsocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {

    @MessageMapping("/message")
    public void publishMessage(String message) {
        log.info("message :: {}", message);
    }
}
