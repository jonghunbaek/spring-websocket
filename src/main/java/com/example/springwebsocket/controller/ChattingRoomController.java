package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.ChattingCreationRoomInfo;
import com.example.springwebsocket.controller.dto.SendMessageRequest;
import com.example.springwebsocket.service.ChattingRoomService;
import com.example.springwebsocket.service.dto.ChattingRoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chatting-rooms")
@RequiredArgsConstructor
@RestController
public class ChattingRoomController {

    private final SimpMessagingTemplate template;

    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public void createChattingRoom(@RequestBody ChattingCreationRoomInfo chattingCreationRoomInfo) {
        chattingRoomService.createChattingRoom(chattingCreationRoomInfo.getName(), chattingCreationRoomInfo.getCapacity());
    }

    @GetMapping
    public List<ChattingRoomInfo> getAllChattingRoom() {
        return chattingRoomService.findAllChattingRooms();
    }

    @MessageMapping("/entrance/{chattingRoomId")
    public void enterChattingRoom(@DestinationVariable Long chattingRoomId,
                                  @Payload SendMessageRequest sendMessageRequest) {

        String enteredMember = chattingRoomService.enterRoom(chattingRoomId, sendMessageRequest.getMemberId());
        template.convertAndSend("/sub/" + chattingRoomId, enteredMember + "님이 입장 했습니다.");
    }

    @MessageMapping("/exit/{chattingRoomId")
    public void exitChattingRoom(@DestinationVariable Long chattingRoomId,
                                 @Payload SendMessageRequest sendMessageRequest) {

        String exitedMember = chattingRoomService.leaveRoom(chattingRoomId, sendMessageRequest.getMemberId());
        template.convertAndSend("/sub/" + chattingRoomId, exitedMember + "님이 퇴장 했습니다.");
    }
}
