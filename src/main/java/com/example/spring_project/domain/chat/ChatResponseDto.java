package com.example.spring_project.domain.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatResponseDto {
    private long no;
    private String email;
    private long squadNo;
    private String nickname;
    private String contents;
    private String notice;

    public ChatResponseDto(Chat chat){
        this.no = chat.getNo();
        this.email = chat.getEmail();
        this.squadNo = chat.getSquadNo();
        this.nickname = chat.getNickname();
        this.contents = chat.getContents();
        this.notice = chat.getNotice();
    }
}
