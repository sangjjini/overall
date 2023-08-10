package com.example.spring_project.controller;

import com.example.spring_project.payload.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/squad/match")
    public String squadMatch(){ return "squad_match"; }
}
