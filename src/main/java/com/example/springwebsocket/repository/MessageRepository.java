package com.example.springwebsocket.repository;

import com.example.springwebsocket.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
