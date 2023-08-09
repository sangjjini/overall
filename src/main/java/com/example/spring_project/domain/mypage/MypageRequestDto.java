package com.example.spring_project.domain.mypage;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class MypageRequestDto {
    private int no;
    private int overall;
    private int age;
    private int height;
    private int weight;
    private int speed;
    private int leftfoot;
    private int rightfoot;

}
