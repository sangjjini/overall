package com.example.spring_project.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    public Member findByEmailAndPassword(String email, String password);

    public Member findByEmail(String email);
}