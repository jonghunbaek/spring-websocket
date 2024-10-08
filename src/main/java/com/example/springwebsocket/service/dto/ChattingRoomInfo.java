package com.example.springwebsocket.service.dto;

import com.example.springwebsocket.entity.ChattingRoom;
import lombok.Getter;

@Getter
public class ChattingRoomInfo {

    private Long roomId;
    private String roomName;

    public ChattingRoomInfo(ChattingRoom room) {
        this.roomId = room.getId();
        this.roomName = room.getName();
    }
}
