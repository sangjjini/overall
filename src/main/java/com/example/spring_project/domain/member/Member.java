package com.example.spring_project.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자 - 파라미터X
@AllArgsConstructor // 기본생성자 - 파라미터O
@Table(name="member")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long code;
    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String nickname;

    @Column(unique = true) //  중복된 값을 가지는 레코드가 삽입되거나 수정되지 않도록 보장됨.
    private String phone;

    private String province;
    private String city;

    public Member(String email){
        super();
        this.email = email;
    }

    // constructor
    public Member(MemberRequestDto memberDto) {
        this.email = memberDto.getEmail();
        this.password = memberDto.getPassword();
        this.name = memberDto.getName();
        this.nickname = memberDto.getNickname();
        this.phone = memberDto.getPhone();
        this.province = memberDto.getProvince();
        this.city = memberDto.getCity();
    }

    // update 메서드 추가
    public void update(MemberRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.name = requestDto.getName();
        this.nickname = requestDto.getNickname();
        this.phone = requestDto.getPhone();
        this.province = requestDto.getProvince();
        this.city = requestDto.getCity();
    }

}