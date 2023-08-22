package com.example.spring_project.domain.member;
// VO = 데이터 저장소.. (수정, 받거나 보냄)

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private int stats;

    public MemberRequestDto(Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.nickname = member.getNickname();
        this.phone = member.getPhone();
        this.stats = member.getStats();

    }
}

