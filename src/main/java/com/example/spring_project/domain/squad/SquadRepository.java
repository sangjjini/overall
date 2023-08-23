package com.example.spring_project.domain.squad;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SquadRepository extends JpaRepository<Squad, Long> {

    Squad findByNo(Long no);
    Squad findByHost(String author);
    Squad findByName(String name);
    List<Squad> findAllByOrderByCreatedAtDesc();
    List<Squad> findAllByOrderByCreatedAtAsc();
    List<Squad> findAllByOrderByStatsAsc();
    List<Squad> findAllByOrderByStatsDesc();
    List<Squad> findAllByNameLikeOrderByCreatedAtDesc(String pattern);
    List<Squad> findAllByNameLikeOrderByCreatedAtAsc(String pattern);
    List<Squad> findAllByNameLikeOrderByStatsAsc(String pattern);
    List<Squad> findAllByNameLikeOrderByStatsDesc(String pattern);
}
