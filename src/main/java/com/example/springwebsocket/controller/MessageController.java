package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.SendMessageRequest;
import com.example.springwebsocket.service.MemberChattingRoomService;
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
public class MessageController {

    private final SimpMessagingTemplate template;

    private final MessageService messageService;
    private final MemberChattingRoomService memberChattingRoomService;

    @MessageMapping("/message/{chattingRoomId}")
    @SendTo("/sub/{chattingRoomId}")
    public void publishMessage(@DestinationVariable Long chattingRoomId,
                               @Payload SendMessageRequest sendMessageRequest) {

        messageService.saveMessage(chattingRoomId, sendMessageRequest.getMemberId(), sendMessageRequest.getMessage());
    }

    @MessageMapping("/message/entrance/{chattingRoomId")
    public void enterChattingRoom(@DestinationVariable Long chattingRoomId,
                                  @Payload SendMessageRequest sendMessageRequest) {

        String enteredMember = memberChattingRoomService.enterRoom(chattingRoomId, sendMessageRequest.getMemberId());
        template.convertAndSend("/sub/" + chattingRoomId, enteredMember + "님이 입장 했습니다.");
    }
}
