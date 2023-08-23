package com.example.spring_project.domain.squad;

import com.example.spring_project.util.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "squad")
@AllArgsConstructor
@Getter
public class Squad extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;
    @Column(nullable = false, length = 320)
    private String host;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 1000)
    private String contents;
    private int stats;

    public Squad(String host, String name, String contents, int stats) {
        this.host = host;
        this.name = name;
        this.contents = contents;
        this.stats = stats;
    }

    public void update(SquadRequestDto squadRequestDto) {
        this.no = squadRequestDto.getNo();
        this.host = squadRequestDto.getHost();
        this.name = squadRequestDto.getName();
        this.contents = squadRequestDto.getContents();
        this.stats = squadRequestDto.getStats();
    }
}
