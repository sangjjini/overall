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
import com.example.spring_project.service.MemberService;
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
    private final MemberService memberService;

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

    @PostMapping("mysquad")
    public Map mySquad(@RequestBody MemberRequestDto dto, WebRequest request){
        JSONObject response = new JSONObject();
        String email = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        List<Joining> list = joiningRepository.findAllByEmailAndStateNotAndStateNotAndStateNot(email,"N","H", "Y");
        List<String> mySquadList = new ArrayList<>();
        List<Integer> overallList = new ArrayList<>();

        int idx = 0;

        for(Joining joining : list){
            long cnt = joiningRepository.countBySquadNoAndStateNotAndStateNotAndStateNot(joining.getSquadNo(), "N", "H","Y");

            if(cnt > 0){
                long no = joining.getSquadNo();
                Squad squad = squadRepository.findByNo(no);
                overallList.add(squad.getStats());
                mySquadList.add(squad.getName());
            }
            idx++;
        }
        response.put("list", mySquadList);
        response.put("squadCnt", idx);
        response.put("overallList", overallList);

        return response.toMap();
    }

    @GetMapping("myMakingList")
    public List<Match> getMyMatch(WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);

        List<Match> matchList = matchRepository.findAllByAuthor(log);
        return matchList;
    }

    @GetMapping("partInList")
    public List<List<Match>> getMyPartInMatch(WebRequest request){
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        List<Joining> joiningList = joiningRepository.findAllByEmailAndStateNotAndStateNotAndStateNot(log, "N", "H", "Y");

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
                else if (sort.equals("date_asc")) {
                    list = matchRepository.findAllByTitleLikeOrderByStartAtAsc(pattern);
                    response.put("list", list);
                }
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

    @GetMapping("{no}")
    public Map getMatchByNo(@PathVariable long no){
        JSONObject response = new JSONObject();
        Match match = matchRepository.getMatchByNo(no);
        Member member = memberRepository.findByEmail(match.getAuthor());
        response.put("match", match);
        response.put("nickname",member.getNickname());
        return response.toMap();
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

            List<Joining> ajoiningList = joiningRepository.findAllBySquadNoAndStateNotAndStateNotAndStateNot(aTeam.getNo(),"N","H", "Y");
            List<Joining> bjoiningList = joiningRepository.findAllBySquadNoAndStateNotAndStateNotAndStateNot(bTeam.getNo(),"N","H", "Y");

            for(Joining joining : ajoiningList){
                System.out.println("A : " + joining.getEmail());
            }
            for(Joining joining : bjoiningList){
                System.out.println("B : " + joining.getEmail());
            }

            boolean chk = false;
            for(int i = 0; i < 1; i++){
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
                // 알람 기능 추가(감이 안잡히네)
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

        response.put("leave", "fail");

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
            Squad squad = squadRepository.findByName(match.getSquadB());
            Joining joining = joiningRepository.findByEmailAndSquadNoAndStateNotAndStateNot(log, squad.getNo(), "N", "H");
            if(joining != null) {
                dto.setSquadB("");
                dto.setDeadline('R');
                matchService.updateMatch(no, dto);
                response.put("leave", "success");
            }else{
                response.put("leave", "fail");
            }
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



    @PostMapping("{no}/matchResult")
    public Map matchResult(@PathVariable long no, @RequestBody String name, WebRequest request){
        JSONObject response = new JSONObject(name);
        String log = (String)request.getAttribute("log", WebRequest.SCOPE_SESSION);
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
            String title = match.getTitle() + "(A팀승)";
            dto.setTitle(title);
            dto.setDeadline('A');
            winner = squadA.getName();
            loser = squadB.getName();
            winnerList = joiningRepository.findAllBySquadNoAndStateNotAndStateNot(squadA.getNo(),"N","H");
            loserList = joiningRepository.findAllBySquadNoAndStateNotAndStateNot(squadB.getNo(),"N","H");
        } else if(squadName.equals(match.getSquadB())){
            String title = match.getTitle() + "(B팀승)";
            dto.setTitle(title);
            dto.setDeadline('B');
            winner = squadB.getName();
            loser = squadA.getName();
            winnerList = joiningRepository.findAllBySquadNoAndStateNotAndStateNot(squadB.getNo(),"N","H");
            loserList = joiningRepository.findAllBySquadNoAndStateNotAndStateNot(squadA.getNo(),"N","H");
        } else {
            String title = match.getTitle() + "(무승부)";
            dto.setTitle(title);
            dto.setDeadline('D');
        }

        for (Joining joining : winnerList) {
            Overall ovr = overallRepository.findByEmail(joining.getEmail());
            int sum = 0;
            int speed =0;
            int physical = 0;
            int overall = 0;

            OverallRequestDto overallRequestDto = new OverallRequestDto(ovr);
            physical += overallRequestDto.getHeight();
            physical += overallRequestDto.getWeight();
            sum = physical/(overallRequestDto.getAge()/10) + 30;
            speed = 100 / (overallRequestDto.getSpeed()) * 9;
            overallRequestDto.setRating(ovr.getRating()+3);

            overall = (sum + speed + overallRequestDto.getRating()) / 3;

            overallService.updateOverall(joining.getEmail(), overallRequestDto);

            Member member = memberRepository.findByEmail(ovr.getEmail());
            MemberRequestDto memberRequestDto = new MemberRequestDto(member);
            memberRequestDto.setStats(overall);

            memberService.updateOverall(member.getEmail(), memberRequestDto);
        }

        for (Joining joining : loserList) {
            Overall ovr = overallRepository.findByEmail(joining.getEmail());
            int sum = 0;
            int speed =0;
            int physical = 0;
            int overall = 0;

            OverallRequestDto overallRequestDto = new OverallRequestDto(ovr);
            physical += overallRequestDto.getHeight();
            physical += overallRequestDto.getWeight();
            sum = physical/(overallRequestDto.getAge()/10) + 30;
            speed = 100 / (overallRequestDto.getSpeed()) * 9;
            overallRequestDto.setRating(ovr.getRating()-1);

            overall = (sum + speed + overallRequestDto.getRating()) / 3;

            overallService.updateOverall(joining.getEmail(), overallRequestDto);

            Member member = memberRepository.findByEmail(ovr.getEmail());
            MemberRequestDto memberRequestDto = new MemberRequestDto(member);
            memberRequestDto.setStats(overall);

            memberService.updateOverall(member.getEmail(), memberRequestDto);
        }

        System.out.println("승 : " + winner);
        System.out.println("패 : " + loser);
        if(!winner.equals("") && !loser.equals("")){
            Squad winSquad = squadRepository.findByName(winner);
            SquadRequestDto winSquadRequestDto = new SquadRequestDto(winSquad);
            winSquadRequestDto.setStats(winSquadRequestDto.getStats() + 3);

            Squad loseSquad = squadRepository.findByName(loser);
            SquadRequestDto loseSquadRequestDto = new SquadRequestDto(loseSquad);
            loseSquadRequestDto.setStats(loseSquadRequestDto.getStats() - 1);

            squadService.updateSquad(winSquad.getNo(), winSquadRequestDto);
            squadService.updateSquad(loseSquad.getNo(), loseSquadRequestDto);
        }

        matchService.updateMatch(no, dto);
        response.put("matchResult", squadName);


        return response.toMap();
    }
}