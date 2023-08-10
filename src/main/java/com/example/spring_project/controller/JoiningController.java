package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningId;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.joining.JoiningRequestDto;
import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.payload.Response;
import com.example.spring_project.service.JoiningService;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class JoiningController {

    private final JoiningRepository joiningRepository;
    private final JoiningService joiningService;
    private final SquadService squadService;
    private final MemberRepository memberRepository;

    @DeleteMapping("squad/{no}/leave")
    public Map leaveJoining(@PathVariable long no) {
        JSONObject response = new JSONObject();
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String log = "email";
        JoiningId joiningId = new JoiningId(log, no);
        joiningService.deleteJoining(joiningId);
        if(joiningRepository.countBySquadNo(no) == 0) {
            squadService.deleteSquad(no);
        }
        return response.put("leave", "success").toMap();
    }

    // 매핑 : squad/8/invite?email=test
    @PostMapping("squad/{no}/invite")
    public Map inviteJoining(@PathVariable long no, @RequestParam String email) {
        JSONObject response = new JSONObject();
        JoiningRequestDto joiningRequestDto = new JoiningRequestDto();
        // 유저 조회 기능 필요
        joiningRequestDto.setEmail(email);
        joiningRequestDto.setSquadNo(no);
        joiningRequestDto.setState("N");
        Joining joining = new Joining(joiningRequestDto);
        joiningRepository.save(joining);
        return response.put("invite", "success").toMap();
    }

    @PostMapping("squad/{no}/accept")
    public Map acceptJoining(@PathVariable long no, @RequestParam String email) {
        JSONObject response = new JSONObject();
        Joining joining = joiningRepository.findByEmailAndSquadNo(email, no);
        JoiningRequestDto joiningRequestDto = new JoiningRequestDto(joining);
        joiningRequestDto.setState("Y");
        joiningService.updateJoining(email, no, joiningRequestDto);
        return response.put("accept", "success").toMap();
    }

    @DeleteMapping("squad/{no}/refuse")
    public Response refuseJoining(@PathVariable long no, @RequestParam String email) {
        JoiningId joiningId = new JoiningId(email, no);
        joiningService.deleteJoining(joiningId);
        return new Response("refuse", "success");
    }

    @GetMapping("squad/{no}/inviting")
    public List<Member> getUserAllInviting(@PathVariable long no) {
        List<Member> members = new ArrayList<>();
        List<Joining> joiningList = joiningRepository.findAllBySquadNoAndState(no, "N");
        for(int i=0; i<joiningList.size(); i++) {
            String email = joiningList.get(i).getEmail();
            Member member = memberRepository.findByEmail(email);
            members.add(member);
        }
        return members;
    }

    @GetMapping("squad/{no}/invited")
    public List<Member> getUserAllInvited(@PathVariable long no) {
        List<Member> members = new ArrayList<>();
        List<Joining> joiningList = joiningRepository.findAllBySquadNoAndState(no, "Y");
        for(int i=0; i<joiningList.size(); i++) {
            String email = joiningList.get(i).getEmail();
            Member member = memberRepository.findByEmail(email);
            members.add(member);
        }
        return members;
    }
}
