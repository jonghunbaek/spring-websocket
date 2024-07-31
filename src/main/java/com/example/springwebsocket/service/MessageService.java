package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.entity.Message;
import com.example.springwebsocket.repository.ChattingRoomRepository;
import com.example.springwebsocket.repository.MemberRepository;
import com.example.springwebsocket.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MemberRepository memberRepository;
    private final ChattingRoomRepository chattingRoomRepository;

    @Transactional
    public void saveMessage(Long chattingRoomId, Long memberId, String message) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(chattingRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 채팅방이 없습니다."));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."));

        messageRepository.save(Message.builder()
                .content(message)
                .member(member)
                .chattingRoom(chattingRoom)
                .build());
    }
}
