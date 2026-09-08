package com.transcript.AcademicResult.controller;

import com.transcript.AcademicResult.dto.requestDto.AcademicResultRequestDto;
import com.transcript.AcademicResult.dto.requestDto.CourseRequestDto;
import com.transcript.AcademicResult.dto.requestDto.SemesterRequestDto;
import com.transcript.AcademicResult.dto.responseDto.AcademicResultResponseDto;
import com.transcript.AcademicResult.dto.responseDto.CourseResponseDto;
import com.transcript.AcademicResult.dto.responseDto.SemesterResponseDto;
import com.transcript.AcademicResult.service.TranscriptService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TranscriptController {

    private final TranscriptService transcriptService;

    @PostMapping("/createAcademicResult")
    public AcademicResultResponseDto createAcademicResult(@ModelAttribute AcademicResultRequestDto academicResultRequestDto) throws IOException {
        return transcriptService.createAcademicResult(academicResultRequestDto);
    }

    @PostMapping("/createSemester/{academicId}")
    public SemesterResponseDto createSemester(@RequestBody SemesterRequestDto semesterRequestDto, @PathVariable Long academicId) {
        return transcriptService.createSemester(semesterRequestDto, academicId);
    }

    @PostMapping("/createCourse/{semesterId}")
    public CourseResponseDto createCourse(@RequestBody CourseRequestDto courseRequestDto, @PathVariable Long semesterId){
        return transcriptService.createCourse(courseRequestDto, semesterId);
    }

    @GetMapping("/getAcademicResult/{id}")
    public AcademicResultResponseDto getAcademicResult(@PathVariable Long id){
        return transcriptService.getAcademicResult(id);
    }

    @GetMapping("/generateTranscript/{id}")
    public HttpEntity<byte[]> generateTranscript(@PathVariable Long id) throws JRException {
        byte[] transcript = transcriptService.generateTranscript(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "transcript.pdf");
        return new HttpEntity<>(transcript, headers);
    }
}
