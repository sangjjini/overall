package com.example.spring_project.domain.matching;

import com.example.spring_project.domain.match.Match;
import com.example.spring_project.domain.squad.Squad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
public class MatchingRequestDto {
    private long matchingNo;
    private Squad squad;
    private Match match;
}
