package com.example.spring_project.domain.squad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepository extends JpaRepository<Squad, Long> {

    public Squad findByMaking(String email);
    public Squad findByNo(Long no);
}
