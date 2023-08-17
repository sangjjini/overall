package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.match.Match;
import com.example.spring_project.domain.match.MatchRepository;
import com.example.spring_project.domain.matching.Matching;
import com.example.spring_project.domain.matching.MatchingRepository;
import com.example.spring_project.domain.mypage.Mypage;
import com.example.spring_project.domain.mypage.MypageRepository;
import com.example.spring_project.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MypageController {
    private final MypageRepository mypageRepository;
    private final JoiningRepository joiningRepository;
    private final MatchingRepository matchingRepository;
    private final MatchRepository matchRepository;
    @GetMapping("mypage/overallList")
    public List<Mypage> getMypageAll() {
        List<Mypage> list = mypageRepository.findAll();
        return list;
    }



}
