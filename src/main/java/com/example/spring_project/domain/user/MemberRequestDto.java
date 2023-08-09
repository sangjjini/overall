package com.example.spring_project.domain.user;
// VO = 데이터 저장소..
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

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