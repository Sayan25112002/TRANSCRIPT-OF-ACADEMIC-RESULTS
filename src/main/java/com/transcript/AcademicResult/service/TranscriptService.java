package com.transcript.AcademicResult.service;

import com.transcript.AcademicResult.dto.requestDto.AcademicResultRequestDto;
import com.transcript.AcademicResult.dto.requestDto.CourseRequestDto;
import com.transcript.AcademicResult.dto.requestDto.SemesterRequestDto;
import com.transcript.AcademicResult.dto.responseDto.AcademicResultResponseDto;
import com.transcript.AcademicResult.dto.responseDto.CourseResponseDto;
import com.transcript.AcademicResult.dto.responseDto.SemesterResponseDto;

import java.io.IOException;

public interface TranscriptService {

    AcademicResultResponseDto createAcademicResult(AcademicResultRequestDto academicResultRequestDto) throws IOException;

    SemesterResponseDto createSemester(SemesterRequestDto semesterRequestDto, Long academicResultId);

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto, Long semesterId);

    AcademicResultResponseDto getAcademicResult(Long id);

    byte[] generateTranscript(Long id);

}
