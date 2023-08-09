package com.example.spring_project.domain.match;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class MatchRequestDto {
    private int no;
    private String title;
    private String contents;
    private String author;
    private char deadline;
//    private String start_at;
//    private String end_at;
//    private String squad_a;
//    private String squad_b;
    private String startAt;
    private String endAt;
    private String squadA;
    private String squadB;
    private String making;

}
