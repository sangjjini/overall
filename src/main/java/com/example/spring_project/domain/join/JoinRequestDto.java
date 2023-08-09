package com.example.spring_project.domain.join;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class JoinRequestDto {
    @Id
    private String join_id;
    private String email;
    private Long squad_no;
    private String state;
}