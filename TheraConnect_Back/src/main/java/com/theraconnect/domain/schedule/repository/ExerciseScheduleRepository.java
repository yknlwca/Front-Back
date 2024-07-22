package com.theraconnect.domain.schedule.repository;

import com.theraconnect.domain.schedule.entity.EMRbase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseScheduleRepository extends JpaRepository<EMRbase, Long> {
}
