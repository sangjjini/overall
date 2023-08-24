package com.example.spring_project.domain.member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponseDto {
    private long code;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private int stats;

    public MemberResponseDto(Member member){
        this.code = member.getCode();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.stats = member.getStats();
    }
}
