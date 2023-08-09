package com.example.spring_project.domain.join;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JoiningRequestDto {
    private String email;
    private Long squadNo;
    private String state;

    public JoiningRequestDto(Joining joining) {
        this.email = joining.getEmail();
        this.squadNo = joining.getSquadNo();
        this.state = joining.getState();
    }
}
