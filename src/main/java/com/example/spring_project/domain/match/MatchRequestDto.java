package com.example.spring_project.domain.match;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
public class MatchRequestDto {
    private long no;
    private String title;
    private String contents;
    private String author;
    private String startAt;
    private String endAt;
    private char deadline;
    private String squadA;
    private String squadB;
}
