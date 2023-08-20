package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.joining.JoiningRequestDto;
import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.domain.squad.SquadRequestDto;
import com.example.spring_project.domain.squad.SquadResponseDto;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SquadController {

    private final SquadRepository squadRepository;
    private final JoiningRepository joiningRepository;
    private final SquadService squadService;

    @PostMapping("squad/make")
    public Map makeSquad(WebRequest request, @RequestParam String name) {
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        SquadRequestDto squadRequestDto = new SquadRequestDto();
        // 스쿼드 이름 중복 검사
        if(squadRepository.findByName(name) == null){
            squadRequestDto.setName(name);
            squadRequestDto.setContents("안녕하세요! "+name+" 입니다.");
            squadRequestDto.setHost(log);
            squadRequestDto.setMaking(log);
            // 스쿼드 생성
            Squad squad = new Squad(squadRequestDto);
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
            response.put("save", squadSave.getNo());
        } else {
            response.put("save", "fail");
        }
        return response.toMap();
    }

    @GetMapping ("squad/{no}")
    public SquadResponseDto getSquadById(@PathVariable long no){
        return new SquadResponseDto(squadRepository.findByNo(no));
    }

    @DeleteMapping("squad/{no}/delete")
    public void deleteSquad(@PathVariable long no) {
        squadService.deleteSquad(no);
    }

    @PostMapping("squad/{no}/update")
    public Map updateSquad(@PathVariable long no, @RequestBody SquadRequestDto squadRequestDto) {
        JSONObject response = new JSONObject();
        Squad squad = squadRepository.findByNo(squadRequestDto.getNo());
        squadRequestDto.setHost(squad.getHost());
        // 스쿼드의 내용만 바꿨을 경우
        if((squad.getName()).equals(squadRequestDto.getName())){
            squadService.updateSquad(no, squadRequestDto);
            response.put("update", "success");
        }else{
            // 스쿼드 이름 중복 검사
            if(squadRepository.findByName(squadRequestDto.getName()) == null){
                squadService.updateSquad(no, squadRequestDto);
                response.put("update", "success");
            } else{
                response.put("update", "fail");
            }
        }
        return response.toMap();
    }

    @GetMapping("squad/my")
    public List<SquadResponseDto> getMySquad(WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        List<Joining> joiningList = joiningRepository.findAllByEmail(log);
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<joiningList.size(); i++){
            Squad squad = squadRepository.findByNo(joiningList.get(i).getSquadNo());
            SquadResponseDto squadResponseDto = new SquadResponseDto(squad);
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }

    @GetMapping("squad/all")
    public List<SquadResponseDto> getAllSquad(){
        List<Squad> squads = squadRepository.findAll();
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<squads.size(); i++){
            SquadResponseDto squadResponseDto = new SquadResponseDto(squads.get(i));
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }
}
