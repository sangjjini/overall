package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.joining.JoiningRequestDto;
import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.domain.squad.SquadRequestDto;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SquadController {

    private final SquadRepository squadRepository;
    private final JoiningRepository joiningRepository;
    private final SquadService squadService;

    @PostMapping("squad/make")
    public Map makeSquad(WebRequest request, @RequestBody SquadRequestDto squadRequestDto) {
        JSONObject response = new JSONObject();
//        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String log = "kevin@gmail.com";

        squadRequestDto.setHost(log);
        squadRequestDto.setMaking(log);
        // 스쿼드 생성
        Squad squad = new Squad(squadRequestDto);
        // 이름 중복 검사 필요
        squadRepository.save(squad);
        Squad squadSave = squadRepository.findByMaking(log);
        // join 생성
        JoiningRequestDto joiningRequestDto = new JoiningRequestDto();
        joiningRequestDto.setEmail(log);
        joiningRequestDto.setSquadNo(squadSave.getNo());
        joiningRequestDto.setState("Y");
        Joining joining = new Joining(joiningRequestDto);
        joiningRepository.save(joining);
        // making 제거
        squadRequestDto.setMaking(null);
        squadService.updateSquad(squadSave.getNo(), squadRequestDto);
        return response.put("save", "success").toMap();
    }

    @GetMapping ("squad/{no}")
    public Squad getSquadById(@PathVariable long no) {
        return squadRepository.findByNo(no);
    }

    @DeleteMapping("squad/{no}/delete")
    public Map deleteSquad(@PathVariable long no) {
        JSONObject response = new JSONObject();
        squadService.deleteSquad(no);
        return response.put("delete", "success").toMap();
    }

    @PostMapping("squad/{no}/update")
    public Map updateSquad(@PathVariable long no, @RequestBody SquadRequestDto squadRequestDto) {
        JSONObject response = new JSONObject();
        squadService.updateSquad(no, squadRequestDto);
        return response.put("update", "success").toMap();
    }

}
