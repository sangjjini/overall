package com.example.spring_project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor // 기본생성자 - 파라미터X
@AllArgsConstructor // 기본생성자 - 파라미터O
@Table(name="member")
@Entity
public class Member {
    @Id
    private String email;
    private String password;
    private String name;
    private String nickname;
    @Column(unique = true)
    private String phone;

    private String province;
    private String city;

    public Member(String email){
        super();
        this.email = email;
    }

    public Member(MemberRequestDto memberDto) {
        this.email = email;
    }

    // update 메서드 추가
    public void update(MemberRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
    }
}
