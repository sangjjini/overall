package com.example.spring_project.domain.squad;

import com.example.spring_project.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class SquadResponseDto {
    private long no;
    private String host;
    private String name;
    private String contents;
    private int stats;
    private String createdAt;

    public SquadResponseDto(Squad squad) {
        this.no = squad.getNo();
        this.host = squad.getHost();
        this.name = squad.getName();
        this.contents = squad.getContents();
        this.stats = squad.getStats();
        this.createdAt = squad.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
