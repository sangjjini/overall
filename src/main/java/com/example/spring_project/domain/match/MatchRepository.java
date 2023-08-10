package com.example.spring_project.domain.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Integer> {
    public Match getMatchByNo(int no);
    public List<Match> findAllByTitleLike(String pattern, Pageable pageable);
    public List<Match> findAllByStartAtLike(String pattern);


}
