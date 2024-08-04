package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.Chatting;
import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.repository.ChattingRepository;
import com.example.springwebsocket.repository.ChattingRoomRepository;
import com.example.springwebsocket.repository.MemberRepository;
import com.example.springwebsocket.service.dto.ChattingRoomInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingRepository chattingRepository;
    private final MemberRepository memberRepository;

    public void createChattingRoom(String roomName, int capacity) {
        ChattingRoom chattingRoom = new ChattingRoom(roomName, capacity);
        chattingRoomRepository.save(chattingRoom);
    }

    public List<ChattingRoomInfo> findAllChattingRooms() {
        return chattingRoomRepository.findAll().stream()
                .map(ChattingRoomInfo::new)
                .toList();
    }

    @Transactional
    public String enterRoom(Long chattingRoomId, Long memberId) {
        ChattingRoom chattingRoom = findChattingRoomById(chattingRoomId);
        Member member = findMemberById(memberId);

        Chatting chatting = Chatting.builder()
                .member(member)
                .chattingRoom(chattingRoom)
                .build();

        chattingRepository.save(chatting);

        return member.getName();
    }

    public String leaveRoom(Long chattingRoomId, Long memberId) {
        ChattingRoom chattingRoom = findChattingRoomById(chattingRoomId);
        Member member = findMemberById(memberId);

        return null;
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 회원이 없습니다."));
    }

    private ChattingRoom findChattingRoomById(Long chattingRoomId) {
        return chattingRoomRepository.findById(chattingRoomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하는 채팅방이 없습니다."));
    }
}
