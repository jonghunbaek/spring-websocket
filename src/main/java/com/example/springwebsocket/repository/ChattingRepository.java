package com.example.springwebsocket.repository;

import com.example.springwebsocket.entity.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {
}
