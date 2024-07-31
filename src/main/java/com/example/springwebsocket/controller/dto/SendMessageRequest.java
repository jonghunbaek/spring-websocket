package com.example.springwebsocket.controller.dto;

import lombok.Getter;

@Getter
public class SendMessageRequest {

    private String message;
    private Long memberId;
}
