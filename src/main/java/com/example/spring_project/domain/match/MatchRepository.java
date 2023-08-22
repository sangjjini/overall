package com.example.spring_project.domain.match;

import com.example.spring_project.domain.squad.Squad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    public Match getMatchByNo(long no);
    //public List<Match> findAllByTitleLike(String pattern, Pageable pageable);
    public List<Match> findAllByTitleLike(String pattern);
    public List<Match> findAllByStartAtLike(String pattern);
    public List<Match> findAllByAuthor(String log);

    public List<Match> findAllBySquadB(String name);

    List<Match> findByOrderByTitleDesc();
    List<Match> findByOrderByTitleAsc();

    List<Match> findByOrderByStartAtDesc();
    List<Match> findByOrderByStartAtAsc();

    Page<Match> findByOrderByStartAtDesc(Pageable pageable);

}
