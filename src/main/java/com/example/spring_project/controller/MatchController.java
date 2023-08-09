package com.example.spring_project.controller;

import com.example.spring_project.domain.match.*;
import com.example.spring_project.payload.Response;
import com.example.spring_project.service.MatchService;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("match")
public class MatchController {

    private final MatchService matchService;
    private final MatchRepository matchRepository;
    private final MatchingRepository matchingRepository;

//    @PostMapping("test")
//    public void test(@RequestBody MatchRequestDto dto){
//        Date st = null;
//            Date et = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try{
//                st = sdf.parse(dto.getStart_at());
//                et = sdf.parse(dto.getEnd_at());
//
//                Timestamp tst = new Timestamp(st.getTime());
//                Timestamp tet = new Timestamp(et.getTime());
//
//                System.out.println("tst : " + tst);
//                System.out.println("tet : " + tet);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        System.out.println("1. " + dto.getTitle());
//        System.out.println("2. " + dto.getContents());
//        System.out.println("3. " + dto.getAuthor());
//        System.out.println("4. " + dto.getSquad_a());
//        System.out.println("5. " + dto.getSquad_b());
//        System.out.println("6. " + dto.getProduce_squad());
//        System.out.println("7. " + dto.getDeadline());
//        System.out.println("8. " + dto.getStart_at());
//        System.out.println("9. " + dto.getEnd_at());
//    }


    @PostMapping(value="write")
    public Map write(@RequestBody MatchRequestDto dto){
        JSONObject response = new JSONObject();

        try {

            Match match = new Match(dto);
            matchRepository.save(match);

//            Matching matching = new Matching(match);
//            matchingRepository.save(matching);

            response.put("write","success");
        }catch (Exception e){
            e.printStackTrace();
            response.put("write","fail");
        }
        return response.toMap();
    }

    @GetMapping("list")
    public Map getMatchAll(){
        JSONObject json = new JSONObject();
        json.put("asd","qwewqe");
        return json.toMap();
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
}
