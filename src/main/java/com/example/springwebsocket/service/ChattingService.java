package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.entity.Chatting;
import com.example.springwebsocket.repository.ChattingRoomRepository;
import com.example.springwebsocket.repository.ChattingRepository;
import com.example.springwebsocket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChattingService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final MemberRepository memberRepository;
    private final ChattingRepository chattingRepository;

    @Transactional
    public String enterRoom(Long chattingRoomId, Long memberId) {
        ChattingRoom chattingRoom = chattingRoomRepository.findById(chattingRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 채팅방이 없습니다."));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."));

        Chatting chatting = Chatting.builder()
                .member(member)
                .chattingRoom(chattingRoom)
                .build();

        chattingRepository.save(chatting);

        return member.getName();
    }
}
