package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.ChattingRoom;
import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.entity.MemberChattingRoom;
import com.example.springwebsocket.repository.ChattingRoomRepository;
import com.example.springwebsocket.repository.MemberChattingRoomRepository;
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
    private final MemberChattingRoomRepository memberChattingRoomRepository;
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

        memberChattingRoomRepository.save(MemberChattingRoom.builder()
                .member(member)
                .chattingRoom(chattingRoom)
                .build());

        return member.getName();
    }

    public String leaveRoom(Long chattingRoomId, Long memberId) {
        memberChattingRoomRepository.deleteByChattingRoomIdAndMemberId(chattingRoomId, memberId);

        return findMemberById(memberId)
                .getName();
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
