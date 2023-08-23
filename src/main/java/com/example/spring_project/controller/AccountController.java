package com.example.spring_project.controller;

import com.example.spring_project.service.RegisterMail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AccountController {

    private RegisterMail registerMail;

    public AccountController(RegisterMail registerMail) {
        this.registerMail = registerMail;
    }


    // 이메일 인증
    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestParam String email) throws Exception {
        System.out.println(email);
        String code = registerMail.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
    }
}
