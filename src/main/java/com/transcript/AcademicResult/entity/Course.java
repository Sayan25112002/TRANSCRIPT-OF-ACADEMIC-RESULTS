package com.transcript.AcademicResult.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String course;

    private String typeOfCourse;

    private String content;

    private String grade;

    private String gradePoint;

    private String credit;

    private String creditPoint;

    @ManyToOne
    @JoinColumn(name = "semesterId")
    @JsonIgnore
    private Semester semester;

}
