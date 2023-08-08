package com.example.spring_project.controller;

import com.example.spring_project.user.MemberVO;
import org.hibernate.service.spi.InjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;


@Controller // 현재 클래스를 스프링에서 관리하는 컨트롤러 bean 으로 생성.
@RequestMapping("/member/*") // 모든 맵핑은 /member/ 상속.
public class MemberController {
    // 로깅을 위한 변수
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

//    @Inject
//    MemberService memberService;


    //01. 로그인 화면
    @RequestMapping("login.do")
    public String login(){
        return "member/login";  // views/member/login.jsp로 포워드
    }

    //02. 로그인 처리
    @RequestMapping("loginCheck.do")
    public ModelAndView loginCheck(@ModelAttribute MemberVO vo, HttpSession session){
        boolean result = memberService.loginCheck(vo, session);
        ModelAndView mav = new ModelAndView();
        if(result){ // 로그인 성공 시
            // index.jsp 로 이동.
            mav.setViewName("home");
            mav.addObject("msg", "success");
        } else { // 로그인 실패 시
            // login.jsp 로 이동.
            mav.setViewName("member/login");
            mav.addObject("msg", "failure");
        }
        return mav;
    }

    // 03. 로그아웃 처리
    @RequestMapping("logout.do")
    public ModelAndView logout(HttpSession session){
        memberService.logout(session);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("member/login");
        mav.addObject("msg","logout");
        return mav;
    }
}
