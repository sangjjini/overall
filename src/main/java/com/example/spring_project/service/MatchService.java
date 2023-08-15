package com.example.spring_project.service;

import com.example.spring_project.domain.match.Match;
import com.example.spring_project.domain.match.MatchRepository;
import com.example.spring_project.domain.match.MatchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;

    @Transactional
    public void updateMatch(long no, MatchRequestDto dto){
        Match match = matchRepository.findById(no).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 경기입니다.")
        );
        match.update(dto);
    }

    @Transactional
    public void deleteMatch(long no){
        matchRepository.deleteById(no);
    }
}