package com.example.spring_project.domain.match;

import com.example.spring_project.domain.squad.Squad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    public Match getMatchByNo(long no);
    public List<Match> findAllByTitleLike(String pattern);
    public List<Match> findAllByAuthor(String log);

    public List<Match> findAllBySquadB(String name);

    List<Match> findByOrderByTitleDesc();
    List<Match> findByOrderByTitleAsc();

    List<Match> findAllByTitleLikeOrderByTitleAsc(String pattern);
    List<Match> findAllByTitleLikeOrderByStartAtAsc(String pattern);

    List<Match> findByOrderByStartAtDesc();
    List<Match> findByOrderByStartAtAsc();

    @Query("SELECT squadMatch FROM Match squadMatch " +
            "JOIN Squad squad ON squadMatch.squadA = squad.name " +
            "ORDER BY squad.stats DESC")
    List<Match> findSquadMatchOrderByDescSquadStats();

    @Query("SELECT squadMatch FROM Match squadMatch " +
            "JOIN Squad squad ON squadMatch.squadA = squad.name " +
            "ORDER BY squad.stats ASC")
    List<Match> findSquadMatchOrderByAscSquadStats();

    @Query("SELECT squadMatch FROM Match squadMatch " +
            "JOIN Squad squad ON squadMatch.squadA = squad.name " +
            "WHERE squadMatch.title like %:keyword%" +
            "ORDER BY squad.stats DESC")
    List<Match> findSquadMatchOrderByDescSquadStats(String keyword);

    @Query("SELECT squadMatch FROM Match squadMatch " +
            "JOIN Squad squad ON squadMatch.squadA = squad.name " +
            "WHERE squadMatch.title like %:keyword%" +
            "ORDER BY squad.stats ASC")
    List<Match> findSquadMatchOrderByAscSquadStats(String keyword);

    Page<Match> findByOrderByStartAtDesc(Pageable pageable);

}
