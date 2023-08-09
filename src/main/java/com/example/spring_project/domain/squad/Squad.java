package com.example.spring_project.domain.squad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "squad")
@AllArgsConstructor
@Getter
public class Squad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    @Column(nullable = false, length = 320)
    private String host;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 1000)
    private String contents;
    @Column(length = 100)
    private String loadedFile;
    @Column(length = 200)
    private String imageUrl;
    @Column(length = 320, unique = true)
    private String making;

    public Squad(SquadRequestDto squadRequestDto) {
        this.host = squadRequestDto.getHost();
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.loadedFile = squadRequestDto.getLoadedFile();
        this.imageUrl = squadRequestDto.getImageUrl();
        this.making = squadRequestDto.getMaking();
    }

    public void update(SquadRequestDto squadRequestDto) {
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.loadedFile = squadRequestDto.getLoadedFile();
        this.imageUrl = squadRequestDto.getImageUrl();
        this.making = squadRequestDto.getMaking();
    }
}
