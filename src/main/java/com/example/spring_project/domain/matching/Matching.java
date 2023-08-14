package com.example.spring_project.domain.matching;

import com.example.spring_project.domain.match.Match;
import com.example.spring_project.domain.squad.Squad;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@Table(name="matching")
@Entity
public class Matching{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="matching_no")
    private long matchingNo;

    @ManyToOne
    @JoinColumn(name="squad_no")
    private Squad squad;

    @ManyToOne
    @JoinColumn(name="match_no")
    private Match match;

    public Matching(Squad squad, Match match){
        this.squad = squad;
        this.match = match;
    }
}
