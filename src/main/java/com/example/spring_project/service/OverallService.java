package com.example.spring_project.service;

import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import com.example.spring_project.domain.overall.OverallRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OverallService {
    private final OverallRepository overallRepository;

    @Transactional
    public void updateOverall(String email, OverallRequestDto overallRequestDto){
        Overall overall = overallRepository.findByEmail(email);
        overall.update(overallRequestDto);
    }
}
