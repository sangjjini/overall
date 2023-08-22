package com.example.spring_project.domain.squad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad, Long> {
    Squad findByNo(Long no);
    Squad findByHost(String author);
    Squad findByName(String name);
}
