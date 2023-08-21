package com.example.spring_project.controller;

import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import com.example.spring_project.domain.overall.OverallRequestDto;
import com.example.spring_project.domain.overall.OverallResponseDto;
import com.example.spring_project.service.OverallService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class OverallController {

    private final OverallService overallService;
    private final OverallRepository overallRepository;


    @GetMapping("overallUpdate/{email}")
    public OverallResponseDto getOverallByEmail(WebRequest request){
        OverallResponseDto overallResponseDto = null;
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if(overallRepository.findByEmail(log)!=null){
        overallResponseDto = new OverallResponseDto(overallRepository.findByEmail(log));
            return overallResponseDto;
        } else {
          overallResponseDto = new OverallResponseDto();
          overallResponseDto.setEmail("none");
          return overallResponseDto;
        }
    }

    @PostMapping("overallUpdate/update")
    public Map updateOverall(WebRequest request, @RequestBody OverallRequestDto overallRequestDto){
        JSONObject response = new JSONObject();
        String log = (String) request.getAttribute("log", WebRequest.SCOPE_SESSION);
        if(overallRepository.findByEmail(log)!=null){
            overallRequestDto.setEmail(log);
            overallService.updateOverall(log, overallRequestDto);

        } else {
            Overall overall = new Overall(overallRequestDto);
            overallRepository.save(overall);
        }
        return response.toMap();

    }
}
