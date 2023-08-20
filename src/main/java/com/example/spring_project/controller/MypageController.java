package com.example.spring_project.controller;


import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MypageController {
    private final OverallRepository overallRepository;
    private final MemberRepository memberRepository;
    @GetMapping("mypage/overallList")
    public Overall getOverallAll(WebRequest request) {

        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        String log = "SIUUU@naver.com";
        Overall list = overallRepository.findByEmail(log);
        return list;
    }

    @GetMapping("mypage/overallList/nickname")
    public Member GetNickname(WebRequest request){

        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        Member member = memberRepository.findByEmail(log);
        return member;
    }




}
