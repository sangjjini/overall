package com.example.spring_project.controller;

import com.example.spring_project.domain.join.Join;
import com.example.spring_project.domain.join.JoinRepository;
import com.example.spring_project.domain.join.JoinRequestDto;
import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.domain.squad.SquadRequestDto;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SquadController {

    private final SquadRepository squadRepository;
    private final JoinRepository joinRepository;
    private final SquadService squadService;

    @PostMapping("squadmake")
    public Map makeSquad(WebRequest request, @RequestBody SquadRequestDto squadRequestDto) {
        JSONObject response = new JSONObject();
        // 스쿼드 생성
        Squad squad = new Squad(squadRequestDto);
        squadRepository.save(squad);
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        Squad squadSave = squadRepository.findByMaking(log);
        // join 생성
        JoinRequestDto joinRequestDto = new JoinRequestDto();
        joinRequestDto.setEmail(log);
        joinRequestDto.setSquad_no(squadSave.getNo());
        joinRequestDto.setState("Y");
        Join join = new Join(joinRequestDto);
        joinRepository.save(join);
        // produce_email 업데이트
        squadRequestDto.setMaking(null);
        squadService.updateSquad(squadSave.getNo(), squadRequestDto);
        return response.put("save", "success").toMap();
    }

}
