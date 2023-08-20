package com.example.spring_project.domain.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    public Match getMatchByNo(long no);
    //public List<Match> findAllByTitleLike(String pattern, Pageable pageable);
    public List<Match> findAllByTitleLike(String pattern);
    public List<Match> findAllByStartAtLike(String pattern);

    public List<Match> findAllByMaking(String making);

    public List<Match> findAllByAuthor(String log);

}
