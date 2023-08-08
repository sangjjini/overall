package com.example.spring_project.domain.match;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name="match")
@Entity
public class Match{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;

    @Column(length = 320, nullable = false)
    private String author;

    @Column(nullable = false)
    private char deadline;

    private String start_at;

    private String end_at;

    private int squad_a;

    private int squad_b;

    private int produce_squad;

    public Match(MatchRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.author = dto.getAuthor();
        this.deadline = dto.getDeadline();
        if(dto.getStart_at() != null) {
            this.start_at = dto.getStart_at();
        }
        if(dto.getEnd_at() != null) {
            this.end_at = dto.getEnd_at();
        }
        this.squad_a = dto.getSquad_a();
        this.squad_b = dto.getSquad_b();
        this.produce_squad = dto.getProduce_squad();
    }

    public void update(MatchRequestDto dto){
        if(dto.getTitle() != null)
            this.title = dto.getTitle();

        if(dto.getContents() != null)
            this.contents = dto.getContents();

        if(dto.getDeadline() >= '0' && dto.getDeadline() <= '2')
            this.deadline = dto.getDeadline();

    }
}
