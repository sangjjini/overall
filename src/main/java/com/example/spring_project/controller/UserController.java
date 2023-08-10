package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
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
//    private final MemberService memberService;
//    private final MemberRepository memberRespository;
//
//    @GetMapping("test")
//    public List<Member> getTest(@RequestParam int pageNumber, @PageableDefault(size=3, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
//        return memberRespository.findAll(pageable.withPage(pageNumber)).getContent();
//
//
////        return userService.findByUsernameAndEmail(username, email);
////        String pattern = "%" + email + "%";
////        return userRespository.findAllByEmailLikeOrderByName(pattern);
////        return userRespository.test1(username, email);
//    }
//
//    @GetMapping("list")
//    public List<User> getUserAll() {
//        List<User> list = memberService.getUserAll();
//        return list;
//    }
//
//    @GetMapping("user/{username}")
//    public User getUserById(@PathVariable String username) {
//        User user = memberService.getUserById(username);
//        return user;
//    }
//
//    @PostMapping("join")
//    public Map join(@RequestBody UserRequestDto userDto) {
//        JSONObject response = new JSONObject();
//        try {
//            memberService.getUserById(userDto.getUsername());
//            response.put("join", "fail");
//        } catch(IllegalArgumentException e) {
//            memberService.createUser(userDto);
//            response.put("join", "success");
//        }
//        return response.toMap();
//    }
//
//    @DeleteMapping("user/{username}/leave")
//    public Map leave(@PathVariable String username) {
//        JSONObject response = new JSONObject();
//        try {
//            memberService.deleteUserById(username);
//            response.put("leave", "success");
//        } catch(Exception e) {
//            e.printStackTrace();
//            response.put("leave", "fail");
//        }
//        return response.toMap();
//    }
//
//    @PutMapping("user/{username}/update")
//    public Map update(@PathVariable String username, @RequestBody UserRequestDto userDto) {
//        memberService.updateUser(username, userDto);
//
//        JSONObject response = new JSONObject();
//        response.put("update", "success");
//        return response.toMap();
//    }

}
