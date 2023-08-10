package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


@Controller // 현재 클래스를 스프링에서 관리하는 컨트롤러 bean 으로 생성.
@SessionAttributes({"log"})
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository; //MemberRepository 주입

    //사용자의 로그인 정보를 받아서 세션에 저장하고 "index" 뷰로 이동.
    @SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestBody Member member, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("index"); // "index" 뷰를 생성
        modelAndView.addObject("log", member.getEmail());
        return modelAndView; // "index" 뷰 반환
    }

    // 사용자의 로그아웃을 처리하고 세션에서 "log" 속성을 삭제한 후 메인페이지로 리다이렉트.
    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status){
        //우선 호출 후
        status.setComplete(); // 현재 세션 상태를 완료로 변경하여 세션 데이터 제거
        //세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION); // "log" 세션 속성 제거
        return "redirect:/"; // 메인페이지로 리다이렉트
    }

//    @GetMapping("/")
//    public Member getUserByEmail(String email){
//        Member member = memberRepository.findById(email).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
//        );
//        return member;
//    }
//
//    @GetMapping("/")
//    public void createUser(MemberRequestDto memberDto) {
//        Member member = new Member(memberDto);
//        memberRepository.save(member); // 엔티티 저장
//    }
}
