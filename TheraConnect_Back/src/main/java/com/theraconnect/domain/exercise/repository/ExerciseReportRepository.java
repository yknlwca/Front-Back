package com.theraconnect.domain.exercise.repository;

import com.theraconnect.domain.exercise.entity.ExerciseReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseReportRepository extends JpaRepository<ExerciseReport, Long> {
}
