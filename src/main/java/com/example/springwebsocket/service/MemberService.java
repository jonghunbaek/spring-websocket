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

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                    .orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));
    }
}
