package com.example.spring_project.user;
// VO = 데이터 저장소..
import lombok.Getter;

import javax.persistence.Id;

@Getter
public class MemberVO {
    private String password;
    private String name;
    @Id
    private String email;
    private String tel;
    private String addr;
    private String admin;

    public void setName(String name) {
        this.name = name;
    }
    public void setPw(String pw) {
        this.password = pw;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
    @Override
    public String toString() {
        return "MemberVO [id =" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", addr=" + addr + ", tel=" + tel + "]";
    }
}