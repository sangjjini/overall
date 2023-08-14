package com.example.spring_project.domain.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RatingRequestDto {
    private String email;
    private String style;
    private String rating;
}
