package com.example.spring_project.domain.joining;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "joining")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(JoiningId.class)
public class Joining {
    @Id
    @Column(name="email", length = 320)
    private String email;
    @Id
    @Column(name="squad_no")
    private long squadNo;
    @Column(nullable = false)
    private String state;
    private int alarm;

    public Joining(JoiningRequestDto joiningRequestDto) {
        this.email = joiningRequestDto.getEmail();
        this.squadNo = joiningRequestDto.getSquadNo();
        this.state = joiningRequestDto.getState();
        this.alarm = joiningRequestDto.getAlarm();
    }

    public void update(JoiningRequestDto joiningRequestDto) {
        this.email = joiningRequestDto.getEmail();
        this.squadNo = joiningRequestDto.getSquadNo();
        this.state = joiningRequestDto.getState();
        this.alarm = joiningRequestDto.getAlarm();
    }
}
