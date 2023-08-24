package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.member.Member;
import com.example.spring_project.domain.member.MemberRepository;
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
    private final MemberRepository memberRepository;

    @PostMapping("squad/make")
    public Map makeSquad(WebRequest request, @RequestParam String name) {
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        // 로그인 확인
        if(log == null){
            response.put("save", "login");
        }else{
            // ovr 확인
            Member member = memberRepository.findByEmail(log);
            if(member.getStats() == 0){
                response.put("save", "stats");
            }else{
                // 스쿼드 이름 중복 검사
                if(squadRepository.findByName(name) == null){
                    Squad squad = new Squad(log, name, "안녕하세요! "+name+" 입니다.", member.getStats());
                    squadRepository.save(squad);
                    // join 생성
                    Joining joining = new Joining(log, squad.getNo(), "Y", 0);
                    joiningRepository.save(joining);
                    response.put("save", squad.getNo());
                } else {
                    response.put("save", "fail");
                }
            }
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
        squadRequestDto.setStats(squad.getStats());
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
        List<Joining> joiningList = joiningRepository.findAllByEmailAndStateNotAndStateNot(log, "N", "H");
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<joiningList.size(); i++){
            Squad squad = squadRepository.findByNo(joiningList.get(i).getSquadNo());
            SquadResponseDto squadResponseDto = new SquadResponseDto(squad);
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }

    @GetMapping("squad/date_up")
    public List<SquadResponseDto> getAllSquadDateUp(@RequestParam(required = false) String keyword){
        List<Squad> squads;
        if(keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            squads = squadRepository.findAllByNameLikeOrderByCreatedAtDesc(pattern);
        }else{
            squads = squadRepository.findAllByOrderByCreatedAtDesc();
        }
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<squads.size(); i++){
            SquadResponseDto squadResponseDto = new SquadResponseDto(squads.get(i));
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }

    @GetMapping("squad/date_down")
    public List<SquadResponseDto> getAllSquadDateDown(@RequestParam(required = false) String keyword){
        List<Squad> squads;
        if(keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            squads = squadRepository.findAllByNameLikeOrderByCreatedAtAsc(pattern);
        }else{
            squads = squadRepository.findAllByOrderByCreatedAtAsc();
        }
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<squads.size(); i++){
            SquadResponseDto squadResponseDto = new SquadResponseDto(squads.get(i));
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }

    @GetMapping("squad/ovr_up")
    public List<SquadResponseDto> getAllSquadOvrUp(@RequestParam(required = false) String keyword){
        List<Squad> squads;
        if(keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            squads = squadRepository.findAllByNameLikeOrderByStatsAsc(pattern);
        }else{
            squads = squadRepository.findAllByOrderByStatsDesc();
        }
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<squads.size(); i++){
            SquadResponseDto squadResponseDto = new SquadResponseDto(squads.get(i));
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }

    @GetMapping("squad/ovr_down")
    public List<SquadResponseDto> getAllSquadOvrDown(@RequestParam(required = false) String keyword){
        List<Squad> squads;
        if(keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword + "%";
            squads = squadRepository.findAllByNameLikeOrderByStatsDesc(pattern);
        }else{
            squads = squadRepository.findAllByOrderByStatsAsc();
        }
        List<SquadResponseDto> squadResponseDtos = new ArrayList<>();
        for(int i=0; i<squads.size(); i++){
            SquadResponseDto squadResponseDto = new SquadResponseDto(squads.get(i));
            squadResponseDtos.add(squadResponseDto);
        }
        return squadResponseDtos;
    }
}
