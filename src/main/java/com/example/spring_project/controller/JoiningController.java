package com.example.spring_project.controller;

import com.example.spring_project.domain.join.Joining;
import com.example.spring_project.domain.join.JoiningId;
import com.example.spring_project.domain.join.JoiningRepository;
import com.example.spring_project.domain.join.JoiningRequestDto;
import com.example.spring_project.service.JoiningService;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JoiningController {

    private final JoiningRepository joiningRepository;
    private final JoiningService joiningService;
    private final SquadService squadService;

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
    public Map refuseJoining(@PathVariable long no, @RequestParam String email) {
        JSONObject response = new JSONObject();
        JoiningId joiningId = new JoiningId(email, no);
        joiningService.deleteJoining(joiningId);
        return response.put("delete", "success").toMap();
    }
}
