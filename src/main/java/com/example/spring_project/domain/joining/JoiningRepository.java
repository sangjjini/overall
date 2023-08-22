package com.example.spring_project.domain.joining;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoiningRepository extends JpaRepository<Joining, JoiningId> {

    Joining findByEmailAndSquadNo(String email, long no);
    long countBySquadNo(long no);
    List<Joining> findAllBySquadNoAndState(long no, String state);
    List<Joining> findAllBySquadNoAndStateNot(long no, String state);
    List<Joining> findAllByEmail(String email);
    List<Joining> findByEmailAndStateNot(String email, String state);
    List<Joining> findAllByEmailAndStateNotAndStateNot(String email, String stateA, String stateB);

    long countBySquadNoAndStateNotAndStateNot(long no, String stateA, String stateB);
    List<Joining> findByEmailAndState(String email, String state);
    List<Joining> findAllBySquadNoAndStateNotAndStateNot(long no, String state_a, String state_b);
}
