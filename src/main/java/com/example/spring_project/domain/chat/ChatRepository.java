package com.example.spring_project.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllBySquadNo(long no);
    int countBySquadNo(long no);
}
