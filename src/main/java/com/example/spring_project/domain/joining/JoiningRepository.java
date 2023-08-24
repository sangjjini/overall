package com.example.spring_project.domain.joining;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoiningRepository extends JpaRepository<Joining, JoiningId> {

    Joining findByEmailAndSquadNo(String email, long no);

    Joining findByEmailAndSquadNoAndStateNotAndStateNot(String email, long no, String stateA, String stateB);

    List<Joining> findAllBySquadNoAndState(long no, String state);
    List<Joining> findAllBySquadNoAndStateNot(long no, String state);
    List<Joining> findAllByEmail(String email);

    List<Joining> findAllByEmailAndStateNotAndStateNot(String email, String stateA, String stateB);
    List<Joining> findAllByEmailAndStateNotAndStateNotAndStateNot(String email, String stateA, String stateB, String stateC);

    int countBySquadNoAndStateNotAndStateNot(long no, String stateA, String stateB);
    int countBySquadNoAndStateNotAndStateNotAndStateNot(long no, String stateA, String stateB, String stateC);
    List<Joining> findAllBySquadNoAndStateNotAndStateNotAndStateNot(long no, String stateA, String stateB, String stateC);
    List<Joining> findByEmailAndState(String email, String state);
    List<Joining> findAllBySquadNoAndStateNotAndStateNot(long no, String state_a, String state_b);
}
