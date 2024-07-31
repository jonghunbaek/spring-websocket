package com.example.springwebsocket.repository;

import com.example.springwebsocket.entity.MemberChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChattingRoomRepository extends JpaRepository<MemberChattingRoom, Long> {
}
