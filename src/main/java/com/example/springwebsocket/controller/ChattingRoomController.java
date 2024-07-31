package com.example.springwebsocket.controller;

import com.example.springwebsocket.controller.dto.ChattingCreationRoomInfo;
import com.example.springwebsocket.service.ChattingRoomService;
import com.example.springwebsocket.service.dto.ChattingRoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat/rooms")
@RequiredArgsConstructor
@RestController
public class ChattingRoomController {

    private final ChattingRoomService chattingRoomService;

    @PostMapping
    public void createChattingRoom(@RequestBody ChattingCreationRoomInfo chattingCreationRoomInfo) {
        chattingRoomService.createChattingRoom(chattingCreationRoomInfo.getName(), chattingCreationRoomInfo.getCapacity());
    }

    @GetMapping
    public List<ChattingRoomInfo> getAllChattingRoom() {
        return chattingRoomService.findAllChattingRooms();
    }
}
