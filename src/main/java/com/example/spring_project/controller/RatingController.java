package com.example.spring_project.controller;

import com.example.spring_project.domain.rating.Rating;
import com.example.spring_project.domain.rating.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class RatingController {
    private final RatingRepository ratingRepository;

    @GetMapping("mypage/rating")
    public List<Rating> getRating() {
        List<Rating> rating = ratingRepository.findAll();
        return rating;
    }
}
