package com.example.spring_project.domain.mypage;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name="overall")
@Entity
public class Mypage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private int overall;
    private int age;
    private int height;
    private int weight;
    private int speed;
    private int leftfoot;
    private int rightfoot;


    public Mypage(MypageRequestDto mypageDto) {
        this.no = mypageDto.getNo();
        this.overall = mypageDto.getOverall();
        this.age = mypageDto.getAge();
        this.height = mypageDto.getHeight();
        this.weight = mypageDto.getWeight();
        this.speed = mypageDto.getSpeed();
        this.leftfoot = mypageDto.getLeftfoot();
        this.rightfoot = mypageDto.getRightfoot();
    }

}
