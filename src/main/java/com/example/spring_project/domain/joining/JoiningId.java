package com.example.spring_project.domain.joining;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class JoiningId implements Serializable {

    private String email;
    private long squadNo;
}
