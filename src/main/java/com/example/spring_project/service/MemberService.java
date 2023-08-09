package com.example.spring_project.service;


import com.example.spring_project.domain.user.Member;
import com.example.spring_project.domain.user.MemberRepository;
import com.example.spring_project.domain.user.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getUserByEmail(String email){
        Member member = memberRepository.findById(email).orElseThrow(
                () -> new IllegalArgumentException("존재하는 유저입니다")
        );
        return member;
    }

    public void createUser(MemberRequestDto memberDto) {
        Member member = new Member(memberDto);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        memberRepository.deleteById(email);
    }

    @Transactional
    public void updateMember(String email, MemberRequestDto memberRequestDto) {
        Member member = getUserByEmail(email);
        member.update(memberRequestDto);
    }
}
