package com.example.spring_project.domain.squad;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SquadResponseDto {
    private long no;
    private String host;
    private String name;
    private String contents;
    private String loadedFile;
    private String imageUrl;

    public SquadResponseDto(Squad squad) {
        this.no = squad.getNo();
        this.host = squad.getHost();
        this.name = squad.getName();
        this.contents = squad.getContents();
        this.loadedFile = squad.getLoadedFile();
        this.imageUrl = squad.getImageUrl();
    }
}
