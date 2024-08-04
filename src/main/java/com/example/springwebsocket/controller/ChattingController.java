package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.SendMessageRequest;
import com.example.springwebsocket.service.ChattingService;
import com.example.springwebsocket.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ChattingController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;
    private final ChattingService chattingService;

    @MessageMapping("/message/{chattingRoomId}")
    @SendTo("/sub/{chattingRoomId}")
    public void publishMessage(@DestinationVariable Long chattingRoomId,
                               @Payload SendMessageRequest sendMessageRequest) {

        messageService.saveMessage(chattingRoomId, sendMessageRequest.getMemberId(), sendMessageRequest.getMessage());
    }

    @MessageMapping("/entrance/{chattingRoomId")
    public void enterChattingRoom(@DestinationVariable Long chattingRoomId,
                                  @Payload SendMessageRequest sendMessageRequest) {

        String enteredMember = chattingService.enterRoom(chattingRoomId, sendMessageRequest.getMemberId());
        template.convertAndSend("/sub/" + chattingRoomId, enteredMember + "님이 입장 했습니다.");
    }
}
