package com.transcript.AcademicResult.mapper;

import com.transcript.AcademicResult.dto.requestDto.AcademicResultRequestDto;
import com.transcript.AcademicResult.dto.responseDto.AcademicResultResponseDto;
import com.transcript.AcademicResult.entity.AcademicResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {
        SemesterMapper.class
})
public interface AcademicMapper {

    AcademicResult toAcademicResult(AcademicResultRequestDto academicResultRequestDto);

    @Mapping(source = "semesters", target = "semesterResponseDtos")
    AcademicResultResponseDto toAcademicResultResponseDto(AcademicResult academicResult);

    List<AcademicResultResponseDto> toAcademicResultResponseDtoList(List<AcademicResult> academicResultList);

}
