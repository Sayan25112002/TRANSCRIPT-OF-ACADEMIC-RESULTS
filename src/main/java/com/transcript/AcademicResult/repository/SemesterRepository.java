package com.transcript.AcademicResult.repository;

import com.transcript.AcademicResult.entity.AcademicResult;
import com.transcript.AcademicResult.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> getByAcademicResult(AcademicResult academicResult);
}
