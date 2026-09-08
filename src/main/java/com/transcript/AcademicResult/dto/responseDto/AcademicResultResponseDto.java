package com.transcript.AcademicResult.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResultResponseDto {

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

    private List<SemesterResponseDto> semesterResponseDtos;

    private String universityLocation;

}
