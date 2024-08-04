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

    private String content;

    @ManyToOne
    private Member member;

    @ManyToOne
    private ChattingRoom chattingRoom;

    @Builder
    private Chatting(String content, Member member, ChattingRoom chattingRoom) {
        this.content = content;
        this.member = member;
        this.chattingRoom = chattingRoom;
    }
}
