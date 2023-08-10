package com.example.spring_project.service;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void deleteUserByEmail(String email) {
        Member member = memberRepository.findById(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 회원이 존재하지 않습니다."));
        memberRepository.delete(member);
    }

    @Transactional
    public void updateMember(String email, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findByEmail(email);
        member.update(memberRequestDto);
    }
}