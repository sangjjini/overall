package com.example.spring_project.service;

import com.example.spring_project.user.MemberDAO;
import com.example.spring_project.user.MemberVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service // 현재 클래스를 스프링에서 관리하는 service bean으로 등록.
public class MemberServiceImpl implements MemberService {

    @Inject
    MemberDAO memberDAO;

    // 01_01. 회원 로그인체크
    @Override
    public boolean loginCheck(MemberVO vo, HttpSession session) {
        boolean result = memberDAO.loginCheck(vo);
        if(result) { // true 일 경우 세션에 등록
            MemberVO vo2 = viewMember(vo);
            //세션 변수 등록
            session.setAttribute("Id", vo2.getId());
            session.setAttribute("Id", vo2.getName());
        }
        return result;
    }
    // 01_02. 회원 로그인 정보
    @Override
    public MemberVO viewMemver(MemberVO vo) {
        return memberDAO.viewMember(vo);
    }
    // 02. 회원 로그아웃
    public void logout(HttpSession session) {
        // 세션 변수 개별 삭제
        // session.removeAttribute("userId");
        // 세션 정보를 초기화 시킴
        session.invalidate();
    }
}
