package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import com.example.spring_project.domain.overall.OverallRequestDto;
import com.example.spring_project.domain.overall.OverallResponseDto;
import com.example.spring_project.service.MemberService;
import com.example.spring_project.service.OverallService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class OverallController {

    private final OverallService overallService;
    private final OverallRepository overallRepository;
    private  final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("overallUpdate/{email}")
    public OverallResponseDto getOverallByEmail(WebRequest request){
        OverallResponseDto overallResponseDto = null;
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if(overallRepository.findByEmail(log)!=null){
        overallResponseDto = new OverallResponseDto(overallRepository.findByEmail(log));
            return overallResponseDto;
        } else {
          overallResponseDto = new OverallResponseDto();
          overallResponseDto.setEmail("none");
          return overallResponseDto;
        }
    }

    @PostMapping("overallUpdate/update")
    public Map updateOverall(WebRequest request, @RequestBody OverallRequestDto overallRequestDto){
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        int rating = 100;
        overallRequestDto.setEmail(log);
        if(overallRepository.findByEmail(log)!=null){
            Overall overall = overallRepository.findByEmail(log);
            rating = overall.getRating();
            overallRequestDto.setRating(rating);
            overallService.updateOverall(log, overallRequestDto);
        } else {
            overallRequestDto.setRating(rating);
            Overall overall = new Overall(overallRequestDto);
            overallRepository.save(overall);
        }


        Member member = memberRepository.findByEmail(log);
        MemberRequestDto memberRequestDto = new MemberRequestDto(member);
        int sum = 0;
        int speed =0;
        int physical = 0;
        int overall = 0;
        physical += overallRequestDto.getHeight();
        physical += overallRequestDto.getWeight();
        sum = physical/(overallRequestDto.getAge()/ 10) + 30;
        System.out.println(sum);
        speed = 100 / (overallRequestDto.getSpeed()) * 9;
        overall = (sum + speed + rating) / 3;
        memberRequestDto.setStats(overall);
        memberService.updateOverall(log, memberRequestDto);

        return response.toMap();

    }
}
