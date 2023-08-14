package com.example.spring_project.domain.joining;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoiningRepository extends JpaRepository<Joining, JoiningId> {

    Joining findByEmailAndSquadNo(String email, long no);
    long countBySquadNo(long no);
    List<Joining> findAllBySquadNoAndState(long no, String state);
    List<Joining> findAllBySquadNoAndStateNot(long no, String state);
}
