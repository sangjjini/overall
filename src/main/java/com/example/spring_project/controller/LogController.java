package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import com.example.spring_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.Map;


@RestController// 현재 클래스를 스프링에서 관리하는 컨트롤러 bean 으로 생성.
@SessionAttributes({"log"})
@RequiredArgsConstructor
public class LogController {
    private final MemberService memberService;
    private final MemberRepository memberRepository; //MemberRepository 주입

    @SessionScope
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Member member = memberRepository.findByEmail(email);

        if (member != null && member.getPassword().equals(password)) {
            session.setAttribute("log", member);
            return "redirect:/";
        } else {
            return "login"; // 로그인 실패시 다시 로그인 페이지로 이동.
        }

//        try{
//            Member member = memberService.getMemberByEmail(loginRequestDto.getEmail());
//            //로그인 성공 처리
//            if(member.getPassword().equals(loginRequestDto.getPassword())) {
//                //로그인 성공 시
//                response.put("result", "loginTrue");
//                response.put("message", "로그인이 완료되었습니다");
//                session.setAttribute("log", member.getEmail());
//                Member loginMember = memberService.wait(loginRequestDto.getEmail(), loginRequestDto.getPassword());
//            } else {
//                // 비밀번호 불일치하면
//                response.put("result", "loginFalse");
//                response.put("message", "유저정보가 존재하지 않습니다.");
//            }
//        } catch (IllformedLocaleException e) {
//            // 유저정보가 존재하지 않을 때
//            response.put("result", "noMember");
//            response.put("message", "유저정보가 존재하지 않습니다.");
//        }
//        return ResponseEntity.ok(response);

    }//end 로그인

//    @GetMapping("/getLoggedInMember")
//    public ResponseEntity<Map<String, String>> getLoggedInMember(@ModelAttribute("log") String log) {
//        Map<String, String> response = new HashMap<>();
//        response.put("loggedInMember", log);
//        return ResponseEntity.ok(response);
//    }


//    // 쿠키에 시간 정보를 주지 않으면 세션 쿠키(브라우저 종료시 모두 종료)
//    Cookie emailCookie = new Cookie("memberEmail", String.valueOf(loginmember.getEmail()));
//    response.addCookie(emailCookie);
//    return "redirect:/";

    // 사용자의 로그아웃을 처리하고 세션에서 "log" 속성을 삭제한 후 메인페이지로 리다이렉트.
    @PostMapping("logout")
    public void logout(WebRequest request, SessionStatus status){
//        session.invalidate(); // 세션 무효화로 로그아웃 처리
        //우선 호출 후
        status.setComplete(); // 현재 세션 상태를 완료로 변경하여 세션 데이터 제거
        //세션 속성을 수정
        request.removeAttribute("log", WebRequest.SCOPE_SESSION); // "log" 세션 속성 제거
    }

//    @GetMapping("/")
//    public Member getUserByEmail(String email){
//        Member member = memberRepository.findById(email).orElseThrow(
//                () -> new IllegalArgumentException("존재하지 않는 유저입니다")
//        );
//        return member;
//    }
//
//    @GetMapping("/")
//    public void createUser(MemberRequestDto memberDto) {
//        Member member = new Member(memberDto);
//        memberRepository.save(member); // 엔티티 저장
//    }

    @SessionScope
    @PostMapping("sign_in")
    public Map sign_in(HttpSession session, @RequestBody MemberRequestDto memberRequestDto){
        JSONObject response = new JSONObject();
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail());
        if(member != null && member.getPassword().equals(memberRequestDto.getPassword())){
            session.setAttribute("log", member.getEmail());
            response.put("sign_in", "success");
        }else{
            response.put("sign_in", "fail");
        }
        return response.toMap();
    }
}
