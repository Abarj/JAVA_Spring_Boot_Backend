package com.example.block21SpringGraphQL.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Course.class)
    private Course course;
}
