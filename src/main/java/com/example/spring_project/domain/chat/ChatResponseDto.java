package com.example.spring_project.domain.chat;

import com.example.spring_project.util.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class ChatResponseDto {
    private long no;
    private String email;
    private long squadNo;
    private String nickname;
    private String contents;
    private String createdAt;

    public ChatResponseDto(Chat chat){
        this.no = chat.getNo();
        this.email = chat.getEmail();
        this.squadNo = chat.getSquadNo();
        this.nickname = chat.getNickname();
        this.contents = chat.getContents();
        this.createdAt = chat.getCreatedAt().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
    }
}
