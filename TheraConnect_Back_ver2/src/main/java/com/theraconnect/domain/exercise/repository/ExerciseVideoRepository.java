package com.theraconnect.domain.exercise.repository;

import com.theraconnect.domain.exercise.entity.ExerciseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseVideoRepository extends JpaRepository<ExerciseVideo, Long> {
}
