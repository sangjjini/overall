package com.example.spring_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RatingController {

    @GetMapping("rating")
    public String rating() {return "rating";}
}
