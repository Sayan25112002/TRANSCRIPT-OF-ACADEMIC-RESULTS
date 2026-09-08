package com.transcript.AcademicResult.mapper;

import com.transcript.AcademicResult.dto.requestDto.SemesterRequestDto;
import com.transcript.AcademicResult.dto.responseDto.SemesterResponseDto;
import com.transcript.AcademicResult.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SemesterMapper {

    Semester toSemester(SemesterRequestDto semesterRequestDto);

    @Mapping(source = "courses", target = "courseResponseDtos")
    SemesterResponseDto toSemesterResponseDto(Semester semester);

    List<SemesterResponseDto> toSemesterResponseDtoList(List<Semester> semesterList);

}
