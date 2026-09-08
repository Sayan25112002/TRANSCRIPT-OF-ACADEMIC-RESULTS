package com.transcript.AcademicResult.service.implementation;

import com.transcript.AcademicResult.dto.requestDto.AcademicResultRequestDto;
import com.transcript.AcademicResult.dto.requestDto.CourseRequestDto;
import com.transcript.AcademicResult.dto.requestDto.SemesterRequestDto;
import com.transcript.AcademicResult.dto.responseDto.AcademicResultResponseDto;
import com.transcript.AcademicResult.dto.responseDto.CourseResponseDto;
import com.transcript.AcademicResult.dto.responseDto.SemesterResponseDto;
import com.transcript.AcademicResult.entity.AcademicResult;
import com.transcript.AcademicResult.entity.Course;
import com.transcript.AcademicResult.entity.Semester;
import com.transcript.AcademicResult.mapper.AcademicMapper;
import com.transcript.AcademicResult.mapper.CourseMapper;
import com.transcript.AcademicResult.mapper.SemesterMapper;
import com.transcript.AcademicResult.repository.AcademicResultRepository;
import com.transcript.AcademicResult.repository.CourseRepository;
import com.transcript.AcademicResult.repository.SemesterRepository;
import com.transcript.AcademicResult.service.TranscriptService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TranscriptServiceImpl implements TranscriptService {

    private final AcademicResultRepository academicResultRepository;
    private final SemesterRepository semesterRepository;
    private final CourseRepository courseRepository;
    private final AcademicMapper academicMapper;
    private final SemesterMapper semesterMapper;
    private final CourseMapper courseMapper;


    @Override
    public AcademicResultResponseDto createAcademicResult(AcademicResultRequestDto academicResultRequestDto) throws IOException {
        AcademicResult academicResult = academicMapper.toAcademicResult(academicResultRequestDto);
        academicResult.setUniversityLogo(saveFile(academicResultRequestDto.getUniversityLogoFile()));
        AcademicResult savedAcademicResult = academicResultRepository.save(academicResult);
        return academicMapper.toAcademicResultResponseDto(savedAcademicResult);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir")+"\\src\\main\\resources\\webapp\\images\\";
        Files.createDirectories(Paths.get(uploadDir));
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        Path path = Paths.get(uploadDir+fileName);
        Files.write(path, file.getBytes());
        return path.toAbsolutePath().toString();
    }

    @Override
    public SemesterResponseDto createSemester(SemesterRequestDto semesterRequestDto, Long academicResultId) {
        Semester semester = semesterMapper.toSemester(semesterRequestDto);
        AcademicResult academicResult = academicResultRepository.findById(academicResultId).orElseThrow(()->new EntityNotFoundException("AcademicResult not found"));
        semester.setAcademicResult(academicResult);
        academicResult.getSemesters().add(semester);
        Semester savedSemester = semesterRepository.save(semester);
        return semesterMapper.toSemesterResponseDto(savedSemester);
    }

    @Override
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto, Long semesterId) {
        Course course = courseMapper.toCourse(courseRequestDto);
        Semester semester = semesterRepository.findById(semesterId).orElseThrow(()->new EntityNotFoundException("Semester not found"));
        course.setSemester(semester);
        semester.getCourses().add(course);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toCourseResponseDto(savedCourse);
    }

    @Override
    public AcademicResultResponseDto getAcademicResult(Long id) {
        AcademicResult academicResult = academicResultRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("AcademicResult not found with id " + id));
        return academicMapper.toAcademicResultResponseDto(academicResult);
    }

    @Override
    public byte[] generateTranscript(Long id) throws JRException {
        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\reports\\";
        Path transcriptPath = Paths.get(filePath,"TranscriptOfAcademicResult.jrxml");
        Path semesterPath = Paths.get(filePath,"SemesterCourses.jrxml");
        JasperReport transcriptReport = JasperCompileManager.compileReport(transcriptPath.toAbsolutePath().toString());
        JasperReport semesterReport = JasperCompileManager.compileReport(semesterPath.toAbsolutePath().toString());
        AcademicResult academicResult = academicResultRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("AcademicResult not found with id " + id));
        List<Semester> semesters = semesterRepository.getByAcademicResult(academicResult);
        Map<String,Object> data = new HashMap<>();
        for(Field field : academicResult.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try {
                data.put(field.getName(), field.get(academicResult));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("data", data);
        parameters.put("semesterCourseReport", semesterReport);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(semesters);
        // Pass JREmptyDataSource(1) so the main template renders EXACTLY once
        JasperPrint jasperPrint = JasperFillManager.fillReport(
                transcriptReport,
                parameters,
                new JREmptyDataSource(1)
        );
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
