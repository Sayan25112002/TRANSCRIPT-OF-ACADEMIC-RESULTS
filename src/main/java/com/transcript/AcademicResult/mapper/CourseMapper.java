package com.transcript.AcademicResult.mapper;

import com.transcript.AcademicResult.dto.requestDto.CourseRequestDto;
import com.transcript.AcademicResult.dto.responseDto.CourseResponseDto;
import com.transcript.AcademicResult.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
    CourseMapper.class
})
public interface CourseMapper {

    Course toCourse(CourseRequestDto courseRequestDto);

    CourseResponseDto toCourseResponseDto(Course course);

    List<CourseResponseDto> toCourseResponseDtoList(List<Course> courseList);

}
