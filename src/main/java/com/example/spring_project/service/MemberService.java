package com.example.spring_project.service;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //필드에 대한 생성자를 자동으로 생성 해줌.
@Service
public class MemberService {

    private final MemberRepository memberRepository;

//    private MailService mailService;


    public List<Member> findByUsernameAndEmail(String name, String email) {
        return memberRepository.findAllBynameAndEmail(name, email);
    } // 해당하는 사용자를 검색하여 리스트로 반환하는 기능 ( memberRepository를 사용하여 데이터베이스에 접근하고 검색을 수행함.)

    public List<Member> getMemberAll() {
        List<Member> list = memberRepository.findAll();
        return list;
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.findById(Long.valueOf(email)).orElseThrow( // Repository에서 회원 정보를 찾아옴
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.") // 예외처리
        );
        return member;
    }

//    public boolean isNicknameValid(String nickname) {
//        return nickname != null && !nickname.trim().isEmpty();
//    }

    @Transactional
    public Map<String, String> createMember(MemberRequestDto memberRequestDto) {
        Map<String, String> result = new HashMap<>();
        Member existingMember = memberRepository.findByEmail(memberRequestDto.getEmail());
        //지정된 사용자 이름을 가진 멤버가 이미 있는지 확인
        if (existingMember != null) {
            result.put("status", "fail");
            result.put("message", "Username already exists");
            return result;
        }

        // 새 구성원 엔티티를 생성하고 DTO에서 해당 속성을 채웁니다
        Member newMember = new Member();
        newMember.setEmail(memberRequestDto.getEmail());
        newMember.setName(memberRequestDto.getName());
        try {
            memberRepository.save(newMember);
            result.put("status", "맴버 생성에 성공했습니다.");
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", "멤버를 생성하는 동안 오류가 발생했습니다");
        }
        return result;
    }

    @Transactional // 데이터베이스 작업을 원자적으로 처리하여 데이터 일관성을 유지하기 위함.
    public void deleteMemberByEmail(String email) { //사용자를 삭제하는 기능을 수행함.
        Member memberToDelete = memberRepository.findByEmail(email); // 주어진 이메일에 해당하는 사용자를 findByEmail 메서드로 조회
        if (memberToDelete != null) {
            memberRepository.delete(memberToDelete); //사용자가 존재하는 경우, memberRepository.delete 메서드로 사용자를 삭제함.
        } else {
            throw new IllegalArgumentException("이메일에 해당하는 사용자가 없습니다.");
        }         // 사용자가 존재하지 않는 경우, 예외를 던져 사용자가 없음을 알림.
    }


    @Transactional
    public void updateUser(String email, MemberRequestDto memberDto) {
        Member member = findByEmail(email);
        member.update(memberDto);
    }

    public Boolean checkNicknameDuplicate(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional
    public void updateOverall(String email, MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(email);
        member.update(memberRequestDto);
    }

    public Boolean checkEmailDuplicate(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean changePassword(String email, String newPassword){
        Member member = memberRepository.findByEmail(email);

        if(member != null){
            member.setPassword(newPassword);
            memberRepository.save(member);
            return true;
        }
        return false;
    }
}