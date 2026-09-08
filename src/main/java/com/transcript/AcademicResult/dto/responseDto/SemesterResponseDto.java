package com.transcript.AcademicResult.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SemesterResponseDto {

    private Long id;

    private String semester;

    private List<CourseResponseDto> courseResponseDtos;

    private String sgpa;

}
