package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.Chatting;
import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.repository.ChattingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final MemberService memberService;
    private final ChattingRoomService chattingRoomService;

    private final ChattingRepository chattingRepository;

    @Transactional
    public void saveMessage(Long chattingRoomId, Long memberId, String message) {
        ChattingRoom chattingRoom = chattingRoomService.findChattingRoomById(chattingRoomId);
        Member member = memberService.findMemberById(memberId);

        chattingRepository.save(Chatting.builder()
                .content(message)
                .member(member)
                .chattingRoom(chattingRoom)
                .build()
        );
    }
}
