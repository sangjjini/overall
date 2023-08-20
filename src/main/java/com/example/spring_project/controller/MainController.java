package com.example.spring_project.controller;

import com.example.spring_project.payload.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

//    @GetMapping("/squad/match")
//    public String squadMatch(){ return "squad_match_form"; }

    @GetMapping("/squad/match")
    public String match(){ return "squad_match"; }


    @GetMapping("/squad/matchList")
    public String squadMatchAll(){ return "squad_match_list"; }

    @GetMapping("squad")
    public String squad(@RequestParam(required = false) long no) { return "squad";}

    @GetMapping("squad/list")
    public String squads(){return "squad_list";}

    @GetMapping("mypage")
    public String mypage(){
        return "mypage";
    }

    @GetMapping("/userrating")
    public String userRating(){
        return "user_rating";
    }

    @GetMapping("/introduce")
    public String introduce() {return "introduce";}

    @GetMapping("/team")
    public String team() {return "team";}

    @GetMapping("/profileUpdate")
    public String profileUpdate() {return "profile_update";}

    @GetMapping("/overallUpdate")
    public String overallUpdate() {return "overall_update";}

    
}
