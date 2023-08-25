package com.example.block26SpringBootAWSLambda.entity;

import lombok.Data;

@Data
public class Character {

    private Long id;

    private String name;

    private Long healthPoints;

    private String[] skills;
}
