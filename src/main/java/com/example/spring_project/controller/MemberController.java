package com.example.spring_project.controller;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller // 현재 클래스를 스프링에서 관리하는 컨트롤러 bean 으로 생성.
@SessionAttributes({"log"})
public class MemberController {
     //사용자의 로그인 정보를 받아서 세션에 저장하고 "index" 뷰로 이동.
    @SessionScope
    @PostMapping("login")
    public ModelAndView login(@RequestBody User user) {
        ModelAndView modelAndView = new ModelAndView("index"); // "index" 뷰를 생성

        //데이터베이스에서 아이디와 패스워드 검증
        boolean isValidUser = authenticateUser(user.getUsername(), user.getPassword());

        if (isValidUser) {
            modelAndView.setViewName("index");
            modelAndView.addObject("log", user.getUsername());
        } else {
            modelAndView.setViewName("login"); // 로그인 페이지로 다시 리다이렉트
            modelAndView.addObject("error", "존재하지 않는 아이디입니다.");
        }

        return modelAndView; // "index" 뷰 반환
    }

    // 사용자의 로그아웃을 처리하고 세션에서 "log" 속성을 삭제한 후 메인페이지로 리다이렉트.
    @PostMapping("logout")
    public String logout(WebRequest request, SessionStatus status){
        status.setComplete(); // 현재 세션 상태를 완료로 변경하여 세션 데이터 제거
        request.removeAttribute("log", WebRequest.SCOPE_SESSION); // "log" 세션 속성 제거
        return "redirect:/Index"; // 메인페이지로 리다이렉트
    }


    // 데이터베이스에서 사용자 정보를 확인.
    private boolean authenticateUser(String username, String password) {
        // 실제 데이터베이스 연동 및 사용자 정보 확인
        return true;
    }
}
