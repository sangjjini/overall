package com.example.spring_project.domain.squad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
public class SquadRequestDto {
    @Id
    private long no;
    private String host;
    private String name;
    private String contents;
    private String loaded_file;
    private String image_url;
    private String making;
}
