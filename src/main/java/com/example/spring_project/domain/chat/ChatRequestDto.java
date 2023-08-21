package com.example.spring_project.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRequestDto {
    private long no;
    private String email;
    private long squadNo;
    private String nickname;
    private String contents;
}
