package com.example.spring_project.domain.joining;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class JoiningResponseDto {
    private String email;
    private Long squadNo;
    private String state;
    private int alarm;
}
