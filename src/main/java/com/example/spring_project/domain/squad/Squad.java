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
    @Column(length = 320)
    private String making;

    public Squad(SquadRequestDto squadRequestDto) {
        this.host = squadRequestDto.getHost();
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.making = squadRequestDto.getMaking();
    }

    public void update(SquadRequestDto squadRequestDto) {
        this.host = squadRequestDto.getHost();
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.making = squadRequestDto.getMaking();
    }
}
