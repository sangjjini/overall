package com.example.spring_project.user;

import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;

@Repository // 현재 클래스를 스프링에서 관리하는 dao bean으로 등록
public class MemberDAOlmpl implements MemberDAO{
    //SqlSession 객체를 스프링에서 생성하여 주입
    // 의존관계 주입(Dependency Injection), 느슨한 결함
    @Inject
    SqlSession sqlSession; // mybatis 실행 객체

    // 01_01. 회원 로그인 체크
    @Override
    public boolean loginCheck(MemberVO vo) {
        String name = sqlSession.selectOne("member.loginCheck", vo);
        return (name == null) ? false : true;
    }

    // 01_02. 회원 로그인 정보
    @Override
    public MemberVO viewMember(MemberVO vo) {
        return sqlSession.selectOne("member.viewMember", vo);
    }

    // 02. 회원 로그아웃
    @Override
    public void logout(HttpSession session) {
    }
}
