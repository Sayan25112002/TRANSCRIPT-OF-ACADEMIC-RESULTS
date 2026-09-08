package com.transcript.AcademicResult.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDto {

    private String course;

    private String typeOfCourse;

    private String content;

    private String grade;

    private String gradePoint;

    private String credit;

    private String creditPoint;

}
