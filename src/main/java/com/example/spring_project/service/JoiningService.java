package com.example.spring_project.service;

import com.example.spring_project.domain.join.Joining;
import com.example.spring_project.domain.join.JoiningId;
import com.example.spring_project.domain.join.JoiningRepository;
import com.example.spring_project.domain.join.JoiningRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class JoiningService {

    private final JoiningRepository joiningRepository;

    @Transactional
    public void deleteJoining(JoiningId joiningId) {
        joiningRepository.deleteById(joiningId);
    }

    @Transactional
    public void updateJoining(String email, long squad_no, JoiningRequestDto joiningRequestDto) {
        Joining joining = joiningRepository.findByEmailAndSquadNo(email, squad_no);
        joining.update(joiningRequestDto);
    }
}
