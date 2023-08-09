package com.example.spring_project.domain.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@NoArgsConstructor
@Table(name="matching")
@Entity
public class Matching{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matching_no;

    private int squadNo;

    private int matchNo;



}
