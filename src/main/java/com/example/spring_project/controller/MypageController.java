package com.example.spring_project.controller;

import com.example.spring_project.domain.mypage.Mypage;
import com.example.spring_project.domain.mypage.MypageRepository;
import com.example.spring_project.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MypageController {
    private final MypageRepository mypageRepository;
    @GetMapping("mypage/list")
    public List<Mypage> getMypageAll() {
        List<Mypage> list = mypageRepository.findAll();
        return list;
    }



}
