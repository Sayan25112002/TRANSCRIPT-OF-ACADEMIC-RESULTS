package com.transcript.AcademicResult.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String semester;

    @OneToMany(mappedBy = "semester")
    @JsonIgnore
    private List<Course> courses;

    private String sgpa;

    @ManyToOne
    @JoinColumn(name = "academicId")
    @JsonIgnore
    private AcademicResult academicResult;

}
