package com.example.spring_project.domain.squad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SquadRequestDto {
    private long no;
    private String host;
    private String name;
    private String contents;
    private int stats;

    public SquadRequestDto(Squad squad) {
        this.no = squad.getNo();
        this.host = squad.getHost();
        this.name = squad.getName();
        this.contents = squad.getContents();
        this.stats = squad.getStats();
    }
}
