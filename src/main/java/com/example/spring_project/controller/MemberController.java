package com.example.spring_project.controller;

import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberRequestDto;
import com.example.spring_project.service.EmailService;
import com.example.spring_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@SessionAttributes("member")
// 모든 메소드에 대하여 @ResponseBody 를 적용하게 됨
@RequestMapping("api/v1/members")
public class MemberController {
    private final MemberService memberService;
    private final EmailService emailService;
    private final MemberRepository memberRepository;

    @GetMapping("test")                                               // size=3 : 한 페이지 당 3항목씩 보여줌.      //이름을 기준으로 내림차순 정렬
    public List<Member> getTest(@RequestParam int pageNumber, @PageableDefault(size=3, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return memberRepository.findAll(pageable.withPage(pageNumber)).getContent(); // getContent() = 실제 조회된 데이터를 반환.
    } //  페이징 처리된 회원 정보를 조회.            // ㄴ 객체에 페이지 번호를 설정하여 해당 페이지의 정보를 조회할 수 있도록 함.

    @GetMapping("list") // 이 경로로 오는 GET 요청을 처리하는 메서드
    public List<Member> getMemberAll() {
        List<Member> list = memberRepository.findAll(); // memberRespository를 이용하여 데이터베이스에 저장된 모든 회원 정보를 조회하고 리스트 형태로 반환.
        return list; // 반환된 회원 정보 리스트인 list를 그대로 반환
    }

    @GetMapping("member/{email}")
    public Member getUserById(@PathVariable String email) { //어노테이션을 통해 URL 경로에서 추출한 email 값을 받아온다.
        Member member = memberService.getMemberByEmail(email); //해당 이메일에 해당하는 회원 정보를 가져온다.
        return member;
    }


//    @PostMapping("/join")
//    public Map join(@RequestBody MemberRequestDto memberDto) {
//        JSONObject response = new JSONObject();
//
//        if (memberRepository.findByEmail(memberDto.getEmail()) == null) { // 기존 멤버를 조회하고, 기존 멤버가 없을 경우에만 새로운 멤버를 생성 후 저장한 후, "join" 값을 "success"로 설정.
//            Member member = new Member(memberDto);
//            memberRepository.save(member);
//            response.put("join", "success");
//        } else {
//            response.put("join", "fail");
//        }
//
//        return response.toMap();
//    }

    @PostMapping("/join")
    public Map join(@RequestBody MemberRequestDto userDto) {
        JSONObject response = new JSONObject();
        try {
            memberService.getMemberByEmail(userDto.getEmail());
            response.put("join", "fail");
        } catch(IllegalArgumentException e) {
            if (!memberService.isNicknameValid(userDto.getNickname())) {
                response.put("join", "fail");
                response.put("message","닉네임이 유효하지 않습니다");
            } else {
            memberService.createMember(userDto);
            response.put("join", "success");
            }
        }
        return response.toMap();
    }

//    @PostMapping("/emailConfirm")
//    public String emailConfirm(@RequestParam String email) throws Exception {
//
//        String confirm = emailService.sendSimpleMessage(email);
//
//        return confirm;
//    }

    @DeleteMapping("member/{email}/leave")
    public Map leave(@PathVariable String email) {
        JSONObject response = new JSONObject();
        try {
            memberService.deleteMemberByEmail(email);
            response.put("leave", "success");
        } catch(Exception e) {
            e.printStackTrace();
            response.put("leave", "fail");
        }
        return response.toMap();
    }

    @PutMapping("member/{email}/update")
    public Map update(@PathVariable String email, @RequestBody MemberRequestDto memberDto) {
        memberService.updateUser(email, memberDto);

        JSONObject response = new JSONObject();
        response.put("update", "success");
        return response.toMap();
    }

}
