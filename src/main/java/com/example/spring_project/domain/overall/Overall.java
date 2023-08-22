package com.example.spring_project.domain.overall;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "overall")
@AllArgsConstructor
@Getter
@Setter
public class Overall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    @Column
    private String email;
    @Column
    private int age;
    @Column
    private int height;
    @Column
    private int weight;
    @Column
    private int speed;
    @Column
    private int rightfoot;
    @Column
    private int leftfoot;
    @Column
    private String playstyle;
    @Column
    private String pos;
    @Column(nullable = false)
    private int rating;

    public Overall(OverallRequestDto overallRequestDto) {
        this.email = overallRequestDto.getEmail();
        this.age = overallRequestDto.getAge();
        this.height = overallRequestDto.getHeight();
        this.weight = overallRequestDto.getWeight();
        this.speed = overallRequestDto.getSpeed();
        this.rightfoot = overallRequestDto.getRightfoot();
        this.leftfoot = overallRequestDto.getLeftfoot();
        this.pos = overallRequestDto.getPos();
        this.playstyle = overallRequestDto.getPlaystyle();
        this.rating = overallRequestDto.getRating();
    }

    public void update(OverallRequestDto overallRequestDto) {
        this.email = overallRequestDto.getEmail();
        this.age = overallRequestDto.getAge();
        this.height = overallRequestDto.getHeight();
        this.weight = overallRequestDto.getWeight();
        this.speed = overallRequestDto.getSpeed();
        this.rightfoot = overallRequestDto.getRightfoot();
        this.leftfoot = overallRequestDto.getLeftfoot();
        this.playstyle = overallRequestDto.getPlaystyle();
        this.pos = overallRequestDto.getPos();
        this.rating = overallRequestDto.getRating();
    }

}
