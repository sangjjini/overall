package com.example.spring_project.domain.rating;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="rating")
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;
    private String style;
    private String rating;


    public Rating(RatingRequestDto ratingDto) {
        this.email = ratingDto.getEmail();
        this.style = ratingDto.getStyle();
        this.rating = ratingDto.getRating();
    }

}
