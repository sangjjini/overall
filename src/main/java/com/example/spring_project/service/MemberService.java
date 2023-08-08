package com.example.spring_project.service;

import com.example.spring_project.user.MemberVO;

import javax.servlet.http.HttpSession;

public interface MemberService {
    // 1 - 1. 회원 로그인 체크
    public boolean loginCheck(MemberVO vo, HttpSession session);

    // 1 - 2. 회원 로그인 정보
    public MemberVO viewMember(MemberVO vo);

    // 01_01. 회원 로그인체크
    boolean loginCheck(MemberVO vo, HttpSession session);

    // 01_02. 회원 로그인 정보
    MemberVO viewMemver(MemberVO vo);

    // 2. 회원 로그아웃
    public void logout(HttpSession session);
}