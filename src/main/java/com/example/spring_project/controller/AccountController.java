package com.example.spring_project.controller;

import com.example.spring_project.service.RegisterMail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final RegisterMail registerMail;
    private static String savedEmailConfirmationCode; // 이전에 발송된 인증코드를 저장하는 변수

    public static void setSavedEmailConfirmationCode(String code) {
        savedEmailConfirmationCode = code;
    }
    // 이메일 인증
    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestParam String email) {
        try{
            String code = registerMail.sendSimpleMessage(email);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return "전자 메일 확인 중 오류가 발생했습니다";
        }
//        System.out.println(email);
//        String code = registerMail.sendSimpleMessage(email);
//        System.out.println("인증코드 : " + code);
//        return code;
    }

    @PostMapping(path = "login/checkEmailConfirm", consumes = "application/json")
    public Map<String, Object> checkEmailConfirm(@RequestBody Map<String, String> requestData) {
        Map<String, Object> resultMap = new HashMap<>();
        String enteredCode = requestData.get("code"); // 클라이언트에서 전송한 인증코드

        System.out.println("Entered Code: " + enteredCode);

        if (enteredCode.equals(savedEmailConfirmationCode)) { // 이전에 저장한 인증코드와 비교
            resultMap.put("result", true); // 인증 성공
        } else {
            resultMap.put("result", false); // 인증 실패
        }

        return resultMap;
    }
}
