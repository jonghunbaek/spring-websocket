package com.example.springwebsocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/message")
    @SendTo("/sub/{chattingRoomId}")
    public void publishMessage(@DestinationVariable String chattingRoomId,  String message) {
        log.info("message :: {}", message);
    }
}
