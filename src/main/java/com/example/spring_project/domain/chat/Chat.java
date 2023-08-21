package com.example.spring_project.domain.chat;

import com.example.spring_project.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="chat")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Chat extends Timestamp{
    @Id
    private long no;
    @Column(length = 320)
    private String email;
    @Column(nullable = false, length = 63)
    private String nickname;
    private long squadNo;
    @Column(length = 500, nullable = false)
    private String contents;

    public Chat(ChatRequestDto chatRequestDto){
        this.no = chatRequestDto.getNo();
        this.email = chatRequestDto.getEmail();
        this.squadNo = chatRequestDto.getSquadNo();
        this.nickname = chatRequestDto.getNickname();
        this.contents = chatRequestDto.getContents();
    }
}
