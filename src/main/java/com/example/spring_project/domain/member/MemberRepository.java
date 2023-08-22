package com.example.spring_project.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //주어진 이메일과 비밀번호에 해당하는 회원을 조회(주어진 조건에 맞는 회원을 찾아 반환함)
    // 유저이름과 이메일을 파라미터로 받아 검색하는 쿼리 메소드 생성
    // SELECT * FROM users WHERE username = ? AND email = ?
    public List<Member> findAllBynameAndEmail(String name, String email);

    // SELECT * FROM users WHERE email LIKE ?
//    public List<Member> findAllByEmailLikeOrderByName(String pattern);

//    public Member findByEmailAndPassword(String email, String password);

    //주어진 이메일에 해당하는 회원을 조회(주어진 이메일에 해당하는 회원을 찾아 반환함)
    public Member findByEmail(String email);

    // 닉네임 중복
    boolean existsByNickname(String nickname);

    //이메일 중복
    boolean existsByEmail(String email);

    //주어진 이메일에 해당하는 회원을 조회(결과 반환 x , 사용자가 입력한 이메일에 해당하는 회원의 존재 여부를 확인하는 용도)
// public void getMemberByEmail(String eamil);

    //주어진 이메일에 해당하는 회원을 삭제(해당 이메일을 가진 회원을 데이터베이스에서 제거함)
//    public void deleteMemberByEmail(String email);

    // 주어진 MemberRequestDto 객체를 기반으로 새로운 회원을 생성(회원 정보를 데이터베이스에 추가하는 역할)
//    void createMember(MemberRequestDto memberDto);

    //주어진 이메일에 해당하는 회원 정보를 주어진 MemberRequestDto 객체의 정보로 업데이트(회원 정보를 수정하는 역할)
//    void updateMember(String email, MemberRequestDto memberDto);

    //모든 회원 정보를 조회하여 리스트로 반환(모든 회원 정보를 조회하는 역할)

    // 내가 추가함
    Member findByCode(long code);

    // 네이티브 쿼리로 메소드 생성

//    @Query(nativeQuery = true, value = "SELECT * FROM members WHERE name = :name AND email = :email")
//    public List<Member> test1(@Param("name") String name, @Param("email") String email);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE members SET password = :password WHERE name = :name AND email = :email")
    void updateMemberPassword(@Param("name") String name, @Param("email") String email, @Param("password") String password);


//    void deleteByEmail(String email); // 물어볼것.
//
//    void updateMember(String email); // 물어볼것
}