package com.example.spring_project.service;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor //필드에 대한 생성자를 자동으로 생성 해줌.
@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public List<Member> findByUsernameAndEmail(String name, String email) {
        return memberRepository.findAllBynameAndEmail(name, email);
    } // 해당하는 사용자를 검색하여 리스트로 반환하는 기능 ( memberRepository를 사용하여 데이터베이스에 접근하고 검색을 수행함.)

    public List<Member> getMemberAll() {
        List<Member> list = memberRepository.findAll();
        return list;
    }

    public Member getMemberByEmail(String email) {
        Member member = memberRepository.findById(Long.valueOf(email)).orElseThrow( // Repository에서 회원 정보를 찾아옴
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.") // 예외처리
        );
        return member;
    }

    @Transactional
    public void createMember(MemberRequestDto memberDto) {// 사용자를 생성하는 기능.
        Member member = new Member(memberDto);
        // MemberRequestDto 객체를 매개변수로 받아서 새로운 Memeber 객체를 생성하고,
        // memberRepository를 통해 데이터베이스에 저장함.

        // 이메일 중복 검사
        if (memberRepository.findByEmail(memberDto.getEmail()) != null) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

    }

    @Transactional // 데이터베이스 작업을 원자적으로 처리하여 데이터 일관성을 유지하기 위함.
    public void deleteMemberByEmail(String Email) { //사용자를 삭제하는 기능을 수행함.
        memberRepository.deleteById(Long.valueOf(Email));
    } // 해당 사용자의 Email에 해당하는 레코드를 memberRepository를 통해 데이터베이스에서 삭제

    @Transactional
    public void updateMamber(String name, MemberRequestDto memberDto) {
        Member member = memberRepository.findByEmail(name);
        member.update(memberDto);
    } // 주어진 사용자 이름()에 해당하는 사용자 정보를 업데이트하는 기능을 수행함.
      // getUserById(name) 메서드를 사용하여 해당 사용자를 가져온 후, userDto 정보를 사용하여 사용자 정보를 업데이트




//    public Member createMember(MemberRequestDto memberDto) {
//        // 데이터 유효성 검사를 JavaScript로 처리하므로 생략
//
//        // 이메일 중복 검사
//        if (memberRepository.findByEmail(memberDto.getEmail()) != null) {
//            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
//        }
//
//        // 비밀번호 암호화 등의 보안 처리
//        Member member = new Member(memberDto);
////        memberRepository.save(member);
////
//        return memberRepository.save(member);
//    }
//
//     @Transactional
//    public void deleteUserByEmail(String email) {
//        Member member = memberRepository.findByEmail(email);
//        memberRepository.delete(member);
//    }
//
//    @Transactional
//    public void updateMember(String email, MemberRequestDto memberRequestDto) {
//        Member member = memberRepository.findByEmail(email);
//        member.update(memberRequestDto);
//    }
//
//    public List<Member> getAllMembers() {
//        return memberRepository.findAll();
//    }
}