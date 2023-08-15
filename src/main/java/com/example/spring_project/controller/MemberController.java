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
public class MemberController {
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

            if (memberRespository.findByEmail(memberDto.getEmail()) == null) { // 기존 멤버를 조회하고, 기존 멤버가 없을 경우에만 새로운 멤버를 생성 후 저장한 후, "join" 값을 "success"로 설정.
                Member member = new Member(memberDto);
                memberRespository.save(member);
                response.put("join", "success");
            } else {
                response.put("join", "fail");
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
