package com.example.spring_project.domain.joining;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JoiningRepository extends JpaRepository<Joining, JoiningId> {

    Joining findByEmailAndSquadNo(String email, long no);

    long countBySquadNo(long no);
}
