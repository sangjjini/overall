
package com.example.spring_project.domain.join;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "join")
@NoArgsConstructor
@AllArgsConstructor
public class Join {
    @Id
    private String join_id;
    @Column(length = 320)
    private String email;
    private long squad_no;
    @Column(nullable = false)
    private String state;

    public Join(JoinRequestDto joinRequestDto){
        this.email = joinRequestDto.getEmail();
        this.squad_no = joinRequestDto.getSquad_no();
        this.state = joinRequestDto.getState();
    }
}