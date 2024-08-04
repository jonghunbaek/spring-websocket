package com.example.springwebsocket.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Chatting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Builder
    private Chatting(Member member, ChattingRoom chattingRoom) {
        this.member = member;
        this.chattingRoom = chattingRoom;
    }
}
