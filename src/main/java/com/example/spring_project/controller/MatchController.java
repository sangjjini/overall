package com.example.spring_project.controller;

import com.example.spring_project.domain.joining.Joining;
import com.example.spring_project.domain.joining.JoiningRepository;
import com.example.spring_project.domain.match.*;
import com.example.spring_project.domain.matching.*;
import com.example.spring_project.domain.member.*;
import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import com.example.spring_project.domain.overall.OverallRequestDto;
import com.example.spring_project.domain.squad.*;
import com.example.spring_project.payload.Response;
import com.example.spring_project.service.MatchService;
import com.example.spring_project.service.OverallService;
import com.example.spring_project.service.SquadService;
import lombok.RequiredArgsConstructor;

import lombok.val;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
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
    private final OverallService overallService;
    private final SquadService squadService;

    private final MatchRepository matchRepository;
    private final MatchingRepository matchingRepository;
    private final SquadRepository squadRepository;
    private final MemberRepository memberRepository;
    private final JoiningRepository joiningRepository;
    private final OverallRepository overallRepository;

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
            if(squad != null) {
                squadList.add(squad);
            }
        }
        List<List<Match>> matchList = new ArrayList<>();

        for(Squad squad : squadList){
            String name = squad.getName();
            matchList.add(matchRepository.findAllBySquadB(name));
        }

        return matchList;
    }
    @GetMapping("list")
    public Map getMatchAll(@RequestParam(required = false) String sort, @RequestParam(required = false) String keyword){
        JSONObject response = new JSONObject();
        List<Match> list = new ArrayList<>();

        if(keyword != null && !keyword.equals("")) {
            String pattern = "%" + keyword + "%";
            if(sort != null){
                if (sort.equals("title_asc")) {
                    list = matchRepository.findAllByTitleLikeOrderByTitleAsc(pattern);
                    response.put("list", list);
                }
//                else if (sort.equals("title_desc")) {
//                    return matchRepository.findByOrderByTitleDesc();
//                }
                else if (sort.equals("date_asc")) {
                    list = matchRepository.findAllByTitleLikeOrderByStartAtAsc(pattern);
                    response.put("list", list);
                }
//                else if (sort.equals("date_desc")) {
//                    return matchRepository.findByOrderByStartAtDesc();
//                }
                else if (sort.equals("overall_asc")) {
                    list = matchRepository.findSquadMatchOrderByAscSquadStats(keyword);
                    response.put("list", list);
                }
                else if (sort.equals("overall_desc")) {
                    list = matchRepository.findSquadMatchOrderByDescSquadStats(keyword);
                    response.put("list", list);
                }
            }else{
                list = matchRepository.findAllByTitleLike(pattern);
                response.put("list", list);
            }
        }else{
            if (sort.equals("title_desc")) {
                list = matchRepository.findByOrderByTitleDesc();
                response.put("list", list);
            } else if (sort.equals("title_asc")) {
                list = matchRepository.findByOrderByTitleAsc();
                response.put("list", list);
            } else if (sort.equals("date_desc")) {
                list = matchRepository.findByOrderByStartAtDesc();
                response.put("list", list);
            } else if (sort.equals("date_asc")) {
                list = matchRepository.findByOrderByStartAtAsc();
                response.put("list", list);
            } else if (sort.equals("overall_asc")) {
                list = matchRepository.findSquadMatchOrderByAscSquadStats();
                response.put("list", list);
            } else if (sort.equals("overall_desc")) {
                list = matchRepository.findSquadMatchOrderByDescSquadStats();
                response.put("list", list);
            } else{
                list = matchRepository.findAll();
                response.put("list", list);
            }
        }

        List<Integer> stats = new ArrayList<>();
        for(Match match : list){
            String squadA = match.getSquadA();
            Squad squad = squadRepository.findByName(squadA);
            stats.add(squad.getStats());
        }
        response.put("stats", stats);

        return response.toMap();
    }
//    @GetMapping("list")
//    public List<Match> getMatchAll(@RequestParam(required = false) String sort, @RequestParam(required = false) String keyword){
//        if(keyword != null && !keyword.equals("")) {
//            String pattern = "%" + keyword + "%";
//            if(sort != null){
//                if (sort.equals("title_asc")) {
//                    return matchRepository.findAllByTitleLikeOrderByTitleAsc(pattern);
//                }
////                else if (sort.equals("title_desc")) {
////                    return matchRepository.findByOrderByTitleDesc();
////                }
//                else if (sort.equals("date_asc")) {
//                    return matchRepository.findAllByTitleLikeOrderByStartAtAsc(pattern);
//                }
////                else if (sort.equals("date_desc")) {
////                    return matchRepository.findByOrderByStartAtDesc();
////                }
//                else if (sort.equals("overall_asc")) {
//                    return matchRepository.findSquadMatchOrderByAscSquadStats(keyword);
//                }
//                else if (sort.equals("overall_desc")) {
//                    return matchRepository.findSquadMatchOrderByDescSquadStats(keyword);
//                }
//            }else{
//                return matchRepository.findAllByTitleLike(pattern);
//            }
//        }else{
//            if (sort.equals("title_desc")) {
//                return matchRepository.findByOrderByTitleDesc();
//            } else if (sort.equals("title_asc")) {
//                return matchRepository.findByOrderByTitleAsc();
//            } else if (sort.equals("date_desc")) {
//                return matchRepository.findByOrderByStartAtDesc();
//            } else if (sort.equals("date_asc")) {
//                return matchRepository.findByOrderByStartAtAsc();
//            } else if (sort.equals("overall_asc")) {
//                return matchRepository.findSquadMatchOrderByAscSquadStats();
//            } else if (sort.equals("overall_desc")) {
//                return matchRepository.findSquadMatchOrderByDescSquadStats();
//            }
//        }
//        return matchRepository.findAll();
//    }
//    @GetMapping("list")
//    public List<Match> getMatchAll(@RequestParam(required = false)String sort, @PageableDefault(size=3) Pageable pageable){
////    public Page<Match> getMatchAll(@RequestParam(required = false) String sort, @PageableDefault(size=2) Pageable pageable){
//        Page<Match> page = matchRepository.findByOrderByStartAtDesc(pageable);
//        System.out.println(page.getTotalPages());
//
//        return matchRepository.findAll();
//    }

//    @GetMapping("list")
//    public List<Match> getMatchAll(@RequestParam(required = false)String sort, @PageableDefault(size=3) Pageable pageable){
//        if(sort.equals("title")){
//            return matchRepository.findByOrderByTitleDesc();
//        }else{
//            return matchRepository.findAll();
//        }
//    }

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
    public Map partInMatch(@PathVariable long no, WebRequest request, @RequestBody String name){
        JSONObject response = new JSONObject(name);
        String log = (String)request.getAttribute("log",WebRequest.SCOPE_SESSION);
        String squadB = response.getString("name");

        //String log = "neymar@gmail.com";
        try {
            Match match = matchRepository.getMatchByNo(no);
            Squad aTeam = squadRepository.findByName(match.getSquadA());
            Squad bTeam = squadRepository.findByName(squadB);

            List<Joining> ajoiningList = joiningRepository.findBySquadNoAndStateNotAndStateNot(aTeam.getNo(),"N","H");
            List<Joining> bjoiningList = joiningRepository.findBySquadNoAndStateNotAndStateNot(bTeam.getNo(),"N","H");



            for(Joining joining : ajoiningList){
                System.out.println("A : " + joining.getEmail());
            }
            for(Joining joining : bjoiningList){
                System.out.println("B : " + joining.getEmail());
            }
            boolean chk = false;
            for(int i = 0; i < 2; i++){
                int cnt = 0;
                String aEmail = ajoiningList.get(i).getEmail();
                for(int j = 0; j < 1; j++) {
                    String bEmail = bjoiningList.get(j).getEmail();
                    if (aEmail.equals(bEmail)) {
                        cnt++;
                    }
                }
                if(cnt > 0){
                    chk = true;
                    break;
                }
            }
            System.out.println("스파이 검거 : " + chk);

            if (match.getSquadB() != null || match.getSquadA().equals(squadB) || chk) {
                response.put("partIn", "fail");
            } else {
                MatchRequestDto dto = new MatchRequestDto();
                dto.setSquadB(squadB);
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


        if(match.getAuthor().equals(log)){
            if(match.getSquadB() == null) {
                matchService.deleteMatch(no);
                response.put("leave", "delete");
            }else {
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

    @PostMapping("mysquad")
    public List<String> mySquad(@RequestBody MemberRequestDto dto, WebRequest request){
        //String email = dto.getEmail();
        String email = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
//        String email = "kevin@gmail.com";
        List<Joining> list = joiningRepository.findAllByEmailAndStateNotAndStateNot(email,"N","H");
        List<String> mySquadList = new ArrayList<>();
        int idx = 1;
        for(Joining joining : list){
            long cnt = joiningRepository.countBySquadNoAndStateNotAndStateNotAndStateNot(joining.getSquadNo(), "N", "H","Y");
            //System.out.println(idx + "팀 포지션 정해진 인원 : " + cnt);
            long no = joining.getSquadNo();
            Squad squad = squadRepository.findByNo(no);
            mySquadList.add(squad.getName());
//            if(cnt == 2){
//                long no = joining.getSquadNo();
//                Squad squad = squadRepository.findByNo(no);
//                mySquadList.add(squad.getName());
//            }
//            idx++;
        }

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

        Squad squadA = squadRepository.findByName(match.getSquadA());
        Squad squadB = squadRepository.findByName(match.getSquadB());

        List<Joining> winnerList = new ArrayList<>();
        List<Joining> loserList = new ArrayList<>();

        String winner = "";
        String loser = "";

        if (squadName.equals(match.getSquadA())) {
            dto.setDeadline('A');
            winner = squadA.getName();
            loser = squadB.getName();
            winnerList = joiningRepository.findBySquadNoAndStateNotAndStateNot(squadA.getNo(),"N","H");
            loserList = joiningRepository.findBySquadNoAndStateNotAndStateNot(squadB.getNo(),"N","H");
        } else if(squadName.equals(match.getSquadB())){
            dto.setDeadline('B');
            winner = squadB.getName();
            loser = squadA.getName();
            winnerList = joiningRepository.findBySquadNoAndStateNotAndStateNot(squadB.getNo(),"N","H");
            loserList = joiningRepository.findBySquadNoAndStateNotAndStateNot(squadA.getNo(),"N","H");
        } else {
            dto.setDeadline('D');
        }

        for (Joining joining : winnerList) {
            Overall ovr = overallRepository.findByEmail(joining.getEmail());

            OverallRequestDto overallRequestDto = new OverallRequestDto(ovr);
            overallRequestDto.setRating(ovr.getRating()+3);
            overallService.updateOverall(joining.getEmail(), overallRequestDto);
        }

        for (Joining joining : loserList) {
            Overall ovr = overallRepository.findByEmail(joining.getEmail());

            OverallRequestDto overallRequestDto = new OverallRequestDto(ovr);
            overallRequestDto.setRating(ovr.getRating()-1);
            overallService.updateOverall(joining.getEmail(), overallRequestDto);
        }
        Squad winSquad = squadRepository.findByName(winner);
        SquadRequestDto winSquadRequestDto = new SquadRequestDto(winSquad);
        winSquadRequestDto.setStats(winSquadRequestDto.getStats() + 3);

        Squad loseSquad = squadRepository.findByName(loser);
        SquadRequestDto loseSquadRequestDto = new SquadRequestDto(loseSquad);
        loseSquadRequestDto.setStats(loseSquadRequestDto.getStats() - 1);

        squadService.updateSquad(winSquad.getNo(), winSquadRequestDto);
        squadService.updateSquad(loseSquad.getNo(), loseSquadRequestDto);

        matchService.updateMatch(no, dto);
        response.put("matchResult", squadName);


        return response.toMap();
    }
}