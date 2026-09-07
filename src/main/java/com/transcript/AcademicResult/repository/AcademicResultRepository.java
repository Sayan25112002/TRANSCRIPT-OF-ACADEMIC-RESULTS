package com.transcript.AcademicResult.repository;

import com.transcript.AcademicResult.entity.AcademicResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicResultRepository extends JpaRepository<AcademicResult, Long> {
}
