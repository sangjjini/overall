package com.example.spring_project.controller;

import com.example.spring_project.domain.match.*;
import com.example.spring_project.domain.matching.Matching;
import com.example.spring_project.domain.matching.MatchingRepository;
import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.payload.Response;
import com.example.spring_project.service.MatchService;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("match")
public class MatchController {

    private final MatchService matchService;
    private final MatchRepository matchRepository;
    private final MatchingRepository matchingRepository;
    private final SquadRepository squadRepository;

    @PostMapping(value="making")
    public Map making(@RequestBody MatchRequestDto dto, WebRequest request){
        JSONObject response = new JSONObject();
        Squad squadA = squadRepository.findByName(dto.getSquadA());
        Squad squadB = squadRepository.findByName(dto.getSquadB());
        //String log = (String) request.getAttribute("log",WebRequest.SCOPE_SESSION);

        if(squadA == null || squadB == null) {
            response.put("making","fail");
            return response.toMap();
        }

        try {
            //dto.setAuthor(log);
            dto.setMaking(dto.getSquadA());
            Squad squad = squadRepository.findByHost(dto.getAuthor());

            // match 생성
            Match match = new Match(dto);
            matchRepository.save(match);

            // matching 생성
            Matching matching = new Matching(squad, match);
            matchingRepository.save(matching);

            response.put("making","success");
        }catch (Exception e){
            e.printStackTrace();
            response.put("making","fail");
        }
        return response.toMap();
    }

    @GetMapping("list/{page}")
    public List<Match> getMatchAll(@PathVariable int page, @RequestParam(required = false)String keyword, @PageableDefault(size=3)Pageable pageable){
        if(keyword != null && !keyword.equals("")){
            String pattern = "%" + keyword + "%";
            return matchRepository.findAllByTitleLike(pattern, pageable.withPage(page - 1));
        }else {
            return matchRepository.findAll(pageable.withPage(page-1)).getContent();
        }
    }

    @GetMapping("{no}")
    public Map getMatchByNo(@PathVariable int no){
        JSONObject response = new JSONObject();
        try {
            Match match = matchRepository.findById(no).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 경기입니다.")
            );
            response.put("result",match);
        }catch (Exception e){
            e.printStackTrace();
            response.put("result","fail");
        }
        return response.toMap();
    }

    @PutMapping(value = "{no}/update")
    public Response update(@PathVariable int no, @RequestBody MatchRequestDto dto, WebRequest request){
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
    public Map deleteMatchByNo(@PathVariable int no, WebRequest request){
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

    @GetMapping("test")
    public Map test(@RequestParam(required = false)String startAt){
        JSONObject response = new JSONObject();
        String pattern = "%" + startAt + "%";
        try{
            List<Match> list = matchRepository.findAllByStartAtLike(pattern);
            response.put("test",list);
        }catch (Exception e){
            e.printStackTrace();
            response.put("test","fail");
        }
        return response.toMap();
    }
}
