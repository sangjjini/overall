package com.example.spring_project.domain.joining;

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
    private int alarm;

    public JoiningRequestDto(Joining joining) {
        this.email = joining.getEmail();
        this.squadNo = joining.getSquadNo();
        this.state = joining.getState();
        this.alarm = joining.getAlarm();
    }

    public JoiningRequestDto(JoiningResponseDto joiningResponseDto){
        this.email = joiningResponseDto.getEmail();
        this.squadNo = joiningResponseDto.getSquadNo();
        this.state = joiningResponseDto.getState();
        this.alarm = joiningResponseDto.getAlarm();
    }
}
