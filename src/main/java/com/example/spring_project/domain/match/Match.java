package com.example.spring_project.domain.match;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Table(name="squad_match")
@Entity
public class Match{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String contents;

    @Column(length = 320, nullable = false)
    private String author;

    @Column(nullable = false)
    private char deadline;

    @Column(name="start_at")
    private String startAt;
    @Column(name="end_at")
    private String endAt;
    @Column(name="squad_a")
    private String squadA;
    @Column(name="squad_b")
    private String squadB;
    @Column(name="making")
    private String making;

  public Match(MatchRequestDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
        this.author = dto.getAuthor();
        this.deadline = dto.getDeadline();
        if(dto.getStartAt() != null) {
            this.startAt = dto.getStartAt();
        }
        if(dto.getEndAt() != null) {
            this.endAt = dto.getEndAt();
        }
        this.squadA = dto.getSquadA();
        this.squadB = dto.getSquadB();
        this.making = dto.getMaking();
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
