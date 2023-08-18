package com.example.spring_project.service;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor //필드에 대한 생성자를 자동으로 생성 해줌.
@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    private EmailService emailService;


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
//        Member existingMember = memberRepository.findByEmail(memberRequestDto.getEmail());
//        if (existingMember != null) {
//            response.put("status", "fail");
//            response.put("message", "이미 가입된 이메일입니다.");
//        } else if (!isNicknameValid(memberRequestDto.getNickname())) {
//            response.put("status", "fail");
//            response.put("message", "닉네임이 유효하지 않습니다");
//        } else {
//            try {
//                // 회원 정보를 저장
//                Member member = new Member(memberRequestDto);
//                member.setEmail(memberRequestDto.getEmail());
//                member.setPassword(memberRequestDto.getPassword());
//                memberRepository.save(member);
//                response.put("status", "success");
//                return response;
//            } catch (Exception e) {
//                e.printStackTrace();
//                response.put("status", "fail");
//                response.put("message", "회원 가입 중 오류가 발생했습니다.");
//            }
//        }
//        return response;
//    }

    @Transactional // 데이터베이스 작업을 원자적으로 처리하여 데이터 일관성을 유지하기 위함.
    public void deleteMemberByEmail(String email) { //사용자를 삭제하는 기능을 수행함.
        Member memberToDelete = memberRepository.findByEmail(email); // 주어진 이메일에 해당하는 사용자를 findByEmail 메서드로 조회
        if(memberToDelete != null){
            memberRepository.delete(memberToDelete); //사용자가 존재하는 경우, memberRepository.delete 메서드로 사용자를 삭제함.
        } else {
            throw new IllegalArgumentException("해당 이메일에 해당하는 사용자가 없습니다.");
        }         // 사용자가 존재하지 않는 경우, 예외를 던져 사용자가 없음을 알림.
    }


    @Transactional
    public void updateUser(String email, MemberRequestDto memberDto) {
        Member member = getMemberByEmail(email);
        member.update(memberDto);
    }

}
//    @Transactional
//    public void updateMember(String email, MemberRequestDto memberDto) {
//        //이메일 유효성 검사와 존재 여부 확인.
//        if(!isValidEmail(email)){
//            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
//        }
//        Member member = memberRepository.findByEmail(email);
//        if(member == null){
//            throw new IllegalArgumentException("해당 이메일에 해당하는 사용자가 존재하지 않습니다.");
//        }
//        member.update(memberDto);
//    }

//    private boolean isValidEmail(String email) throws Exception {
//        // 정규식 패턴을 사용하여 이메일 형식을 검사
//        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
//
//        boolean isValid = email.matches(regex);
//
//        if (isValid) {
//            // 이메일 형식이 올바른 경우의 처리
//            String confirmationCode = emailService.sendSimpleMessage(email);
//            response.put("isValidEmail", true);
//            response.put("confirmationCode", confirmationCode);
//        } else {
//            // 이메일 형식이 잘못된 경우의 처리
//            response.put("isValidEmail", false);
//            response.put("message", "올바른 이메일 형식이 아닙니다.");
//        }
//        return isValid;
////        return email.matches(emailPattern);
//    }





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