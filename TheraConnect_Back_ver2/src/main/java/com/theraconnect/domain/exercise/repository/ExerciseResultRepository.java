package com.theraconnect.domain.exercise.repository;

import com.theraconnect.domain.exercise.entity.ExerciseResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseResultRepository extends JpaRepository<ExerciseResult, Long> {
}
