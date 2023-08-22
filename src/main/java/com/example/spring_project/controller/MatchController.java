package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.match.*;
import com.example.spring_project.domain.matching.*;
import com.example.spring_project.domain.member.*;
import com.example.spring_project.domain.squad.*;
import com.example.spring_project.payload.Response;
import com.example.spring_project.service.MatchService;
import lombok.RequiredArgsConstructor;

import lombok.val;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("squad/match")
public class MatchController {

    private final MatchService matchService;
    private final MatchRepository matchRepository;
    private final MatchingRepository matchingRepository;
    private final SquadRepository squadRepository;
    private final MemberRepository memberRepository;
    private final JoiningRepository joiningRepository;

    @PostMapping("make")
    public Map makeMatch(WebRequest request, @RequestParam String title, @RequestParam String squadA, @RequestParam String startAt, @RequestParam String endAt){
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        MatchRequestDto matchRequestDto = new MatchRequestDto();
        matchRequestDto.setTitle(title);
        matchRequestDto.setContents("재밌게 풋살하실 분들 찾습니다!");
        matchRequestDto.setAuthor(log);
        matchRequestDto.setSquadA(squadA);
        matchRequestDto.setStartAt(startAt);
        matchRequestDto.setEndAt(endAt);
        matchRequestDto.setDeadline('R');

        Squad squad = squadRepository.findByName(matchRequestDto.getSquadA());

        Match match = new Match(matchRequestDto);
        matchRepository.save(match);

        Matching matching = new Matching(squad,match);
        matchingRepository.save(matching);

        List<Match> matchSave = matchRepository.findAllByAuthor(log);
        int cnt = matchSave.size();

        System.out.println(cnt);
        response.put("make", matchSave.get(cnt-1).getNo());
        return response.toMap();
    }

    @GetMapping("my")
    public List<Match> getMyMatch(WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        List<Match> matchList = matchRepository.findAllByAuthor(log);
        return matchList;
    }

    @GetMapping("partInList")
    public List<List<Match>> getMyPartInMatch(WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        List<Joining> joiningList = joiningRepository.findAllByEmail(log);

        List<Squad> squadList = new ArrayList<>();
        for(Joining joining : joiningList){
            long no = joining.getSquadNo();
            Squad squad = squadRepository.findByNo(no);
            squadList.add(squad);
        }
        List<List<Match>> matchList = new ArrayList<>();

        for(Squad squad : squadList){
            String name = squad.getName();
            matchList.add(matchRepository.findAllBySquadB(name));
        }

        return matchList;
    }

    @GetMapping("list")
    public List<Match> getMatchAll(@RequestParam(required = false)String keyword, @PageableDefault(size=3) Pageable pageable){
        if(keyword != null && !keyword.equals("")){
            String pattern = "%" + keyword + "%";
            return matchRepository.findAllByTitleLike(pattern);
        }else {
            return matchRepository.findAll();
        }
    }

    @GetMapping("{no}")
    public Match getMatchByNo(@PathVariable long no){
        return matchRepository.getMatchByNo(no);
    }

    @PutMapping(value = "{no}/update")
    public Map update(@PathVariable long no, @RequestBody MatchRequestDto dto, WebRequest request){
        JSONObject response = new JSONObject();
        String log = (String)request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if(log.equals(dto.getAuthor())){
            try {
                matchService.updateMatch(no, dto);
                response.put("update", "success");
            } catch (Exception e){
                e.printStackTrace();
                response.put("update", "fail");
            }
        }else{
            response.put("update", "fail");
        }
        return response.toMap();
    }


    @DeleteMapping("{no}/delete")
    public Map deleteMatchByNo(@PathVariable long no, WebRequest request){
        JSONObject response = new JSONObject();
        String log = (String)request.getAttribute("log",WebRequest.SCOPE_SESSION);

        try{
            Match match = matchRepository.findById(no).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 경기입니다.")
            );
            if(match.getAuthor().equals(log)){
                matchService.deleteMatch(no);
                response.put("delete","success");
            }else{
                response.put("delete","fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("delete","fail");
        }
        return response.toMap();
    }

    // 매치 신청
    @PostMapping("{no}/partIn")
    public Map partInMatch(@PathVariable long no, WebRequest request){
        JSONObject response = new JSONObject();
        String log = (String)request.getAttribute("log",WebRequest.SCOPE_SESSION);
        //String log = "neymar@gmail.com";
        try {
            Squad squad = squadRepository.findByHost(log);
            Match match = matchRepository.getMatchByNo(no);

            if (match.getSquadB() != null) {
                response.put("partIn", "fail");
            } else {
                MatchRequestDto dto = new MatchRequestDto();
                dto.setSquadB(squad.getName());
                dto.setDeadline('F');
                matchService.updateMatch(no, dto);
                response.put("partIn", "success");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("partIn", "fail");
        }
        return response.toMap();
    }
    // 매치 퇴장
    @PostMapping("{no}/leave")
    public Map matchLeave(@PathVariable long no, WebRequest request, @RequestBody String name){

        JSONObject response = new JSONObject(name);
        Match match = matchRepository.getMatchByNo(no);
        MatchRequestDto dto = new MatchRequestDto();
        // log
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String squadName = response.getString("name");
        System.out.println(log);
        System.out.println(match.getSquadB());
        System.out.println(squadName);

        if(match.getAuthor().equals(log)){
            if(match.getSquadB() == null) {
                matchService.deleteMatch(no);
                response.put("leave", "delete");
            }else {
                System.out.println("방장 튐");
                Squad squad = squadRepository.findByName(match.getSquadB());
                dto.setAuthor(squad.getHost());
                dto.setDeadline('R');
                dto.setSquadA(match.getSquadB());
                dto.setSquadB("");
                matchService.updateMatch(no, dto);
                response.put("leave", "success");
            }
        }else {
            if (match.getSquadB().equals(squadName)) {
                dto.setSquadB("");
                dto.setDeadline('R');
                matchService.updateMatch(no, dto);
                response.put("leave", "success");
            } else {
                response.put("leave", "fail");
            }
        }
        return response.toMap();
    }

//    @PostMapping("/getMatch")
//    public Map getTeam(@RequestBody String squad) throws UnsupportedEncodingException {
//        JSONObject response = new JSONObject();
//        String tmpA = squad.split("&")[0];
//        String tmpB = squad.split("&")[1];
//        String sqA = "";
//        String sqB = "";
//        int sqaIdx = tmpA.indexOf("=");
//        int sqbIdx = tmpB.indexOf("=");
//
//        if(sqaIdx != -1){
//            sqA = tmpA.substring(sqaIdx + 1);
//            if(sqA.length() > 0){
//                sqA = URLDecoder.decode(tmpA, StandardCharsets.UTF_8).split("=")[1];
//            }
//        }
//        if(sqbIdx != -1){
//            sqB = tmpB.substring(sqbIdx + 1);
//            if(sqB.length() > 0){
//                sqB = URLDecoder.decode(tmpB, StandardCharsets.UTF_8).split("=")[1];
//            }
//        }
//
//        Squad squadA = squadRepository.findByName(sqA);
//        Squad squadB = squadRepository.findByName(sqB);

//        if(squadA.getImageUrl() == null){
//            response.put("squadALogo","/images/noimage.jpeg");
//        }else{
//            response.put("squadALogo",squadA.getImageUrl());
//        }
//
//        if(squadB.getImageUrl() == null){
//            response.put("squadBLogo","/images/noimage.jpeg");
//        }else{
//            response.put("squadBLogo",squadB.getImageUrl());
//        }
//        return response.toMap();
//    }

    @PostMapping("mysquad")
    public List<String> mySquad(@RequestBody MemberRequestDto dto, WebRequest request){
        //String email = dto.getEmail();
        String email = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        String email = "kevin@gmail.com";
        List<Joining> list = joiningRepository.findAllByEmailAndStateNotAndStateNot(email,"N","H");
        List<String> mySquadList = new ArrayList<>();
        int idx = 1;
        for(Joining joining : list){
            long cnt = joiningRepository.countBySquadNoAndStateNotAndStateNot(joining.getSquadNo(), "N", "H");
            System.out.println(idx++ + "번 : " + cnt);

            if(cnt == 2){
                long no = joining.getSquadNo();
                Squad squad = squadRepository.findByNo(no);
                mySquadList.add(squad.getName());
            }
        }

//        for(Joining joining : list){
//            System.out.println(joining);
//            long no = joining.getSquadNo();
//            Squad squad = squadRepository.findByNo(no);
//            mySquadList.add(squad.getName());
//        }
        return mySquadList;
    }

    @PostMapping ("test")
    public void test(@RequestBody MatchRequestDto dto){
        System.out.println(dto.getTitle());
        System.out.println(dto.getAuthor());
        System.out.println(dto.getContents());
        System.out.println(dto.getStartAt());
        System.out.println(dto.getEndAt());
        System.out.println(dto.getSquadA());
        System.out.println(dto.getSquadB());
    }

    @PostMapping("{no}/matchResult")
    public Map matchResult(@PathVariable long no, @RequestBody String name){
        JSONObject response = new JSONObject(name);
        //String log = (String)request.getAttribute("log", WebRequest.SCOPE_SESSION);
        String squadName = response.getString("name");

        Match match = matchRepository.getMatchByNo(no);

        MatchRequestDto dto = new MatchRequestDto();

        System.out.println("squadName : " + squadName);
        System.out.println("squadA : " + match.getSquadA());
        System.out.println("squadB : " + match.getSquadB());
        if (squadName.equals(match.getSquadA())) {
            dto.setDeadline('A');
        } else if(squadName.equals(match.getSquadB())){
            dto.setDeadline('B');
        } else {
            dto.setDeadline('D');
        }

        matchService.updateMatch(no, dto);

        response.put("matchResult", squadName);

        return response.toMap();
    }
}