package com.example.springwebsocket.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MemberChattingRoom {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Builder
    private MemberChattingRoom(Member member, ChattingRoom chattingRoom) {
        this.member = member;
        this.chattingRoom = chattingRoom;
    }
}
