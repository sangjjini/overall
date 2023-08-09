package com.example.spring_project.service;

import com.example.spring_project.domain.squad.Squad;
import com.example.spring_project.domain.squad.SquadRepository;
import com.example.spring_project.domain.squad.SquadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SquadService {

    private final SquadRepository squadRepository;

    @Transactional
    public void updateSquad(long no, SquadRequestDto squadRequestDto) {
        Squad squad = squadRepository.findByNo(no);
        squad.update(squadRequestDto);
    }

    @Transactional
    public void deleteSquad(long no) {
        squadRepository.deleteById(no);
    }
}
