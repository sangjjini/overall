package com.example.spring_project.service;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

     @Transactional
    public void deleteUserByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        memberRepository.delete(member);
    }

    @Transactional
    public void updateMember(String email, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findByEmail(email);
        member.update(memberRequestDto);
    }

    public Member createMember(MemberRequestDto memberDto) {
        // 데이터 유효성 검사를 JavaScript로 처리하므로 생략

        // 이메일 중복 검사
        if (memberRepository.findByEmail(memberDto.getEmail()) != null) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화 등의 보안 처리
        Member member = new Member(memberDto);
//        member.setEmail(memberDto.getEmail());
//        member.setPassword(memberDto.getPassword());
//        member.setName(memberDto.getName());
//        member.setNickname(memberDto.getNickname());
//        member.setPhone(memberDto.getPhone());
//        member.setProvince(memberDto.getProvince());
//        member.setCity(memberDto.getCity());
        memberRepository.save(member);

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 다른 메서드들도 유사하게 작성 가능
}