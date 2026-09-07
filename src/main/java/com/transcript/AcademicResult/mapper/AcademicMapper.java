package com.transcript.AcademicResult.mapper;

import com.transcript.AcademicResult.dto.requestDto.AcademicResultRequestDto;
import com.transcript.AcademicResult.dto.responseDto.AcademicResultResponseDto;
import com.transcript.AcademicResult.entity.AcademicResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicMapper {

    AcademicResult toAcademicResult(AcademicResultRequestDto academicResultRequestDto);

    AcademicResultResponseDto toAcademicResultResponseDto(AcademicResult academicResult);

    List<AcademicResultResponseDto> toAcademicResultResponseDtoList(List<AcademicResult> academicResultList);

}
