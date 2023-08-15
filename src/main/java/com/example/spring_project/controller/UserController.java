package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import com.example.spring_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;
    private final MemberRepository memberRespository;

    @GetMapping("test")
    public List<Member> getTest(@RequestParam int pageNumber, @PageableDefault(size=3, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return memberRespository.findAll(pageable.withPage(pageNumber)).getContent();


//        return userService.findByUsernameAndEmail(username, email);
//        String pattern = "%" + email + "%";
//        return userRespository.findAllByEmailLikeOrderByName(pattern);
//        return userRespository.test1(username, email);
    }

    @GetMapping("list")
    public List<Member> getMemberAll() {
        List<Member> list = memberRespository.findAll();
        return list;
    }

    @GetMapping("member/{email}")
    public Member getUserById(@PathVariable String email) {
        Member member = memberRespository.findByEmail(email);
        return member;
    }

        @PostMapping("join")
        public Map join(@RequestBody MemberRequestDto memberDto) {
            JSONObject response = new JSONObject();
            try {
                memberRespository.findByEmail(memberDto.getEmail());
                response.put("join", "fail");
            } catch(IllegalArgumentException e) {
//                memberRespository.createMember(memberDto);
                response.put("join", "success");
            }
            return response.toMap();
        }

    @DeleteMapping("member/{email}/leave")
    public Map leave(@PathVariable String email) {
        JSONObject response = new JSONObject();
        try {
//            memberRespository.deleteById();
            response.put("leave", "success");
        } catch(Exception e) {
            e.printStackTrace();
            response.put("leave", "fail");
        }
        return response.toMap();
    }

    @PutMapping("member/{email}/update")
    public Map update(@PathVariable String email, @RequestBody MemberRequestDto memberDto) {
//        memberRespository.updateMember(email, memberDto);

        JSONObject response = new JSONObject();
        response.put("update", "success");
        return response.toMap();
    }

}
