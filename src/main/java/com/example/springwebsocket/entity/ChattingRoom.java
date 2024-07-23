package com.example.springwebsocket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ChattingRoom {

    public static final int CAPACITY_MAX = 10;
    public static final int CAPACITY_MIN = 2;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int capacity;

    public ChattingRoom(String name, int capacity) {
        validateCapacity(capacity);
        this.name = name;
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (capacity > CAPACITY_MAX || capacity < CAPACITY_MIN) {
            throw new IllegalArgumentException("채팅방에 입장 가능한 인원은 2명 이상 10명 이하입니다.");
        }
    }
}
