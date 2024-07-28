package com.example.springwebsocket.service;

import com.example.springwebsocket.entity.Member;
import com.example.springwebsocket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(String name) {
        memberRepository.save(new Member(name));
    }
}
