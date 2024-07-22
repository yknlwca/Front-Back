package com.theraconnect.domain.schedule.repository;

import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalScheduleRepository extends JpaRepository<MedicalSchedule, Long> {
}
