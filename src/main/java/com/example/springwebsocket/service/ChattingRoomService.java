package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;

    public void createChattingRoom(String roomName, int capacity) {
        ChattingRoom chattingRoom = new ChattingRoom(roomName, capacity);
        chattingRoomRepository.save(chattingRoom);
    }
}
