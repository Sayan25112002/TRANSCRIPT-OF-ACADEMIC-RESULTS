package com.transcript.AcademicResult.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class AcademicResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String universityLogo;

    private String universityName;

    private String universityAddress1;

    private String universityAddress2;

    private String docNumber;

    private String date;

    private String name;

    private String regNumber;

    private String degree;

    private String result;

    private String awardDate;

    private String collegeName;

    @OneToMany(mappedBy = "academicResult")
    @JsonIgnore
    private List<Semester> semesters;

    private String universityLocation;

}
