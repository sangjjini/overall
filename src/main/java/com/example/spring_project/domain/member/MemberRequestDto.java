package com.example.spring_project.domain.member;
// VO = 데이터 저장소.. (수정, 받거나 보냄)
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberRequestDto {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String province;
    private String city;
}