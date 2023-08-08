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
    private String start_at;
    private String end_at;
    private int squad_a;
    private int squad_b;
    private int making;
//    private String startAt;
//    private String endAt;
//    private int squadA;
//    private int squadB;
//    private int produceSquad;
}