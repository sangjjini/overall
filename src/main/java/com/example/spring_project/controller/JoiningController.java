package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningId;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.joining.JoiningRequestDto;
import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
import com.example.spring_project.domain.member.MemberResponseDto;
import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.domain.squad.SquadRequestDto;
import com.example.spring_project.service.JoiningService;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JoiningController {

    private final JoiningRepository joiningRepository;
    private final JoiningService joiningService;
    private final SquadService squadService;
    private final MemberRepository memberRepository;
    private final SquadRepository squadRepository;

    @DeleteMapping("joining/{no}/leave")
    public void leaveJoining(WebRequest request, @PathVariable long no) {
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String log = "kevin@gmail.com";

        JoiningId joiningId = new JoiningId(log, no);
        joiningService.deleteJoining(joiningId);
        Squad squad = squadRepository.findByNo(no);
        // 스쿼드의 방장이 나갔을 경우
        if(squad.getHost().equals(log)) {
            // 스쿼드에 아무도 없을 경우
            if(joiningRepository.countBySquadNo(no) == 0) {
                squadService.deleteSquad(no);
            } else {
                SquadRequestDto squadRequestDto = new SquadRequestDto(squad);
                String host = joiningRepository.findAllBySquadNoAndStateNot(no, "N").get(0).getEmail();
                squadRequestDto.setHost(host);
                squadService.updateSquad(no, squadRequestDto);
            }
        }
    }

    // 매핑 : squad/8/invite?email=test
    @PostMapping("joining/{no}/invite")
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

    @PostMapping("joining/{no}/accept")
    public void acceptJoining(@PathVariable long no, @RequestParam long code) {
        String email = memberRepository.findByCode(code).getEmail();
        Joining joining = joiningRepository.findByEmailAndSquadNo(email, no);
        JoiningRequestDto joiningRequestDto = new JoiningRequestDto(joining);
        joiningRequestDto.setState("Y");
        joiningService.updateJoining(email, no, joiningRequestDto);
    }

    @DeleteMapping("joining/{no}/refuse")
    public void refuseJoining(@PathVariable long no, @RequestParam long code) {
        String email = memberRepository.findByCode(code).getEmail();
        JoiningId joiningId = new JoiningId(email, no);
        joiningService.deleteJoining(joiningId);
    }

    @GetMapping("joining/{no}/inviting")
    public List<MemberResponseDto> getUserAllInviting(@PathVariable long no) {
        List<MemberResponseDto> members = new ArrayList<>();
        List<Joining> joiningList = joiningRepository.findAllBySquadNoAndState(no, "N");
        for(int i=0; i<joiningList.size(); i++) {
            String email = joiningList.get(i).getEmail();
            Member member = memberRepository.findByEmail(email);
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            members.add(memberResponseDto);
        }
        return members;
    }

    @GetMapping("joining/{no}/invited")
    public List<MemberResponseDto> getUserAllInvited(@PathVariable long no) {
        List<MemberResponseDto> members = new ArrayList<>();
        List<Joining> joiningList = joiningRepository.findAllBySquadNoAndStateNot(no, "N");
        for(int i=0; i<joiningList.size(); i++) {
            String email = joiningList.get(i).getEmail();
            Member member = memberRepository.findByEmail(email);
            MemberResponseDto memberResponseDto = new MemberResponseDto(member);
            members.add(memberResponseDto);
        }
        return members;
    }
}
