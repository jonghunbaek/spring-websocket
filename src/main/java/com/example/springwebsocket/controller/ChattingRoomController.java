package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.ChattingRoomInfo;
import com.example.springwebsocket.service.ChattingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/chat/rooms")
@RequiredArgsConstructor
@RestController
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public void createChattingRoom(@RequestBody ChattingRoomInfo chattingRoomInfo) {
        chattingRoomService.createChattingRoom(chattingRoomInfo.getName(), chattingRoomInfo.getCapacity());
    }
}
