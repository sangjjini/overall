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


}
