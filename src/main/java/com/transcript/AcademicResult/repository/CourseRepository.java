package com.transcript.AcademicResult.repository;

import com.transcript.AcademicResult.entity.Course;
import com.transcript.AcademicResult.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> getBySemester(Semester semester);
}
