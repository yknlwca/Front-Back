package com.theraconnect.domain.schedule.repository;

import com.theraconnect.domain.schedule.entity.ExerciseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseScheduleRepository extends JpaRepository<ExerciseSchedule, Long> {
}
