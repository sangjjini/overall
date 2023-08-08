package com.example.spring_project.domain.squad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "squad")
@AllArgsConstructor
@Getter
public class Squad {
    @Id
    private long no;
    @Column(nullable = false, length = 320)
    private String host;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 1000)
    private String contents;
    @Column(length = 100)
    private String loaded_file;
    @Column(length = 200)
    private String image_url;
    @Column(length = 320)
    private String making;

    public Squad(SquadRequestDto squadRequestDto) {
        this.host = squadRequestDto.getHost();
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.loaded_file = squadRequestDto.getLoaded_file();
        this.image_url = squadRequestDto.getImage_url();
        this.making = squadRequestDto.getMaking();
    }

    public void update(SquadRequestDto squadRequestDto) {
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.loaded_file = squadRequestDto.getLoaded_file();
        this.image_url = squadRequestDto.getImage_url();
        this.making = squadRequestDto.getMaking();
    }
}
