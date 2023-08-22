package com.example.spring_project.domain.overall;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OverallRequestDto {
    private Long no;
    private String email;
    private int age;
    private int height;
    private int weight;
    private int speed;
    private int rightfoot;
    private int leftfoot;
    private String pos;
    private String playstyle;
    private int rating;

    public OverallRequestDto(Overall overall) {
        this.no = overall.getNo();
        this.email = overall.getEmail();
        this.age = overall.getAge();
        this.height = overall.getHeight();
        this.weight = overall.getWeight();
        this.speed = overall.getSpeed();
        this.rightfoot = overall.getRightfoot();
        this.leftfoot = overall.getLeftfoot();
        this.playstyle = overall.getPlaystyle();
        this.pos = overall.getPos();
        this.rating = overall.getRating();
    }
}
