package com.example.spring_project.domain.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Table(name="matching")
@Entity
public class Matching {

    @Id
    private int matchNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="match_no", referencedColumnName = "no")
    private Match match;

    public Matching(Match match){
        this.match = match;
    }

}
