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
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping(value="making")
    public Map making(@RequestBody MatchRequestDto dto, WebRequest request){
        JSONObject response = new JSONObject();
//        Squad squadA = squadRepository.findByName(dto.getSquadA());
//        Squad squadB = squadRepository.findByName(dto.getSquadB());
        //String log = (String) request.getAttribute("log",WebRequest.SCOPE_SESSION);

//        if(squadA == null || squadB == null) {
//            response.put("making","fail");
//            return response.toMap();
//        }
        System.out.println("st : " + dto.getStartAt());
        System.out.println("et : " + dto.getEndAt());

        try {
            if(dto.getSquadA() != null && dto.getSquadB() != null) {
                dto.setDeadline('1');
            }else {
                dto.setDeadline('0');
            }

            if(dto.getSquadB().equals("")){
                dto.setSquadB(null);
            }

            //dto.setAuthor(log);
            dto.setMaking(dto.getSquadA());
            Squad squad = squadRepository.findByName(dto.getMaking());
            if(squad != null) {
                // match 생성
                Match match = new Match(dto);
                matchRepository.save(match);

                //matching 생성
                Matching matching = new Matching(squad, match);
                matchingRepository.save(matching);
                dto.setMaking(null);
                response.put("making", "success");
            }else{
                response.put("making","존재하지 않는 스쿼드입니다.");
            }
        }catch (Exception e){
            e.printStackTrace();
            response.put("making","fail");
        }
        return response.toMap();
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
    public Map getMatchByNo(@PathVariable long no){
        JSONObject response = new JSONObject();
        Match match = matchRepository.getMatchByNo(no);
        Squad squadA = squadRepository.findByName(match.getSquadA());
        Squad squadB = squadRepository.findByName(match.getSquadB());
        Member member = memberRepository.findByEmail(match.getAuthor());

        response.put("title", match.getTitle());
        response.put("startAt", match.getStartAt());
        response.put("endAt", match.getEndAt());
        response.put("author", member.getNickname());
        response.put("deadline", match.getDeadline());


        if(squadA != null) {
            response.put("squadA",squadA.getName());
            response.put("squadA_logo", squadA.getImageUrl());
        }

        if(squadB != null){
            response.put("squadB",squadB.getName());
            response.put("squadB_logo", squadB.getImageUrl());
        }

        return response.toMap();
    }

    @PutMapping(value = "{no}/update")
    public Response update(@PathVariable long no, @RequestBody MatchRequestDto dto, WebRequest request){
        //String log = (String)request.getAttribute("log", WebRequest.SCOPE_SESSION);
        //if(log.equals(dto.getAuthor())){
            try {
                matchService.updateMatch(no, dto);
                return new Response("update", "success");
            } catch (Exception e){
                e.printStackTrace();
                return new Response("update", "fail");
            }
//        }else{
//            return new Response("update", "작성자만 수정할 수 있습니다.");
//        }
    }


    @DeleteMapping("{no}/delete")
    public Map deleteMatchByNo(@PathVariable long no, WebRequest request){
        JSONObject response = new JSONObject();
        //String log = (String)request.getAttribute("log",WebRequest.SCOPE_SESSION);
        try{
            Match match = matchRepository.findById(no).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 경기입니다.")
            );
            //if(match.getAuthor().equals(log)){
                matchService.deleteMatch(no);
                response.put("delete","success");
            //}
        }catch (Exception e){
            e.printStackTrace();
            response.put("delete","fail");
        }
        return response.toMap();
    }

    // 매치 신청
    @PostMapping("{no}/apply")
    public Map applyMatch(@PathVariable long no, @RequestBody MatchRequestDto dto){
        JSONObject response = new JSONObject();
        Match match = matchRepository.getMatchByNo(no);
        if(match.getSquadB() != null){
            response.put("apply", "참가할 수 없는 매치입니다.");
            return response.toMap();
        }
        try {
            Squad squad = squadRepository.findByName(dto.getSquadB());
            dto.setSquadB(squad.getName());
            dto.setDeadline('1');
            matchService.updateMatch(no, dto);
            response.put("apply","success");
        }catch (Exception e){
            e.printStackTrace();
            response.put("apply","fail");
        }
        return response.toMap();
    }
    @PostMapping("/getMatch")
    public Map getTeam(@RequestBody String squad) throws UnsupportedEncodingException {
        JSONObject response = new JSONObject();
        String tmpA = squad.split("&")[0];
        String tmpB = squad.split("&")[1];
        String sqA = "";
        String sqB = "";
        int sqaIdx = tmpA.indexOf("=");
        int sqbIdx = tmpB.indexOf("=");

        if(sqaIdx != -1){
            sqA = tmpA.substring(sqaIdx + 1);
            if(sqA.length() > 0){
                sqA = URLDecoder.decode(tmpA, StandardCharsets.UTF_8).split("=")[1];
            }
        }
        if(sqbIdx != -1){
            sqB = tmpB.substring(sqbIdx + 1);
            if(sqB.length() > 0){
                sqB = URLDecoder.decode(tmpB, StandardCharsets.UTF_8).split("=")[1];
            }
        }

        Squad squadA = squadRepository.findByName(sqA);
        Squad squadB = squadRepository.findByName(sqB);

        if(squadA.getImageUrl() == null){
            response.put("squadALogo","/images/noimage.jpeg");
        }else{
            response.put("squadALogo",squadA.getImageUrl());
        }

        if(squadB.getImageUrl() == null){
            response.put("squadBLogo","/images/noimage.jpeg");
        }else{
            response.put("squadBLogo",squadB.getImageUrl());
        }
        return response.toMap();
    }
    // 매치 퇴장
    @PostMapping("{no}/leave")
    public Map matchLeave(@PathVariable long no, WebRequest request, @RequestBody String name){
        //String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        JSONObject response = new JSONObject(name);
        Match match = matchRepository.getMatchByNo(no);
        // log
        String log = "kevin@gmail.com";
        String squadName = response.getString("name");
//        if(match.getAuthor().equals(response.getString("name"))){
//            System.out.println("매치 만든놈이 도망간다!");
//        }
        System.out.println("sqB : " + match.getSquadB().equals(squadName));
        System.out.println("squadName : " + squadName);
        if(match.getSquadB().equals(squadName)){
            MatchRequestDto dto = new MatchRequestDto();
            dto.setSquadB(null);
            dto.setDeadline('0');
            matchService.updateMatch(no, dto);
            response.put("leave","success");
        }else {
            response.put("leave", "fail");
        }
        return response.toMap();
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

}
