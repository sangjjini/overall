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

    private String making;
    private String startAt;
    private String endAt;
    private String squadA;
    private String squadB;
}
