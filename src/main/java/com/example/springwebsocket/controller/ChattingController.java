package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.SendMessageRequest;
import com.example.springwebsocket.service.ChattingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final ChattingService chattingService;

    @MessageMapping("/message/{chattingRoomId}")
    @SendTo("/sub/{chattingRoomId}")
    public void publishMessage(@DestinationVariable Long chattingRoomId,
                               @Payload SendMessageRequest sendMessageRequest) {

        chattingService.saveMessage(chattingRoomId, sendMessageRequest.getMemberId(), sendMessageRequest.getMessage());
    }
}
