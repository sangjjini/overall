package com.example.spring_project.controller;

import com.example.spring_project.domain.overall.Overall;
import com.example.spring_project.domain.overall.OverallRepository;
import com.example.spring_project.domain.overall.OverallRequestDto;
import com.example.spring_project.domain.overall.OverallResponseDto;
import com.example.spring_project.service.OverallService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class OverallController {

    private final OverallService overallService;
    private final OverallRepository overallRepository;


    @GetMapping("overallUpdate/{email}")
    public OverallResponseDto getOverallByEmail(@PathVariable String email){
        OverallResponseDto overallResponseDto = null;
        if(overallRepository.findByEmail(email)!=null){
        overallResponseDto = new OverallResponseDto(overallRepository.findByEmail(email));
            return overallResponseDto;
        } else {
          overallResponseDto = new OverallResponseDto();
          overallResponseDto.setEmail("none");
          return overallResponseDto;
        }
    }

    @PostMapping("overallUpdate/{email}/update")
    public Map updateOverall(@PathVariable String email, @RequestBody OverallRequestDto overallRequestDto){
        JSONObject response = new JSONObject();
        if(overallRepository.findByEmail(email)!=null){
            overallService.updateOverall(email, overallRequestDto);

        } else {
            Overall overall = new Overall(overallRequestDto);
            overallRepository.save(overall);
        }
        return response.toMap();


    }
}
