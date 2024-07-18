package com.theraconnect.domain.schedule.repository;

import com.theraconnect.domain.member.entity.Theraphist;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalScheduleRepository extends JpaRepository<MedicalSchedule, Long> {

    // TH의 CD의 CT시간 이후 진료 일정
    List<MedicalSchedule> findByTheraphistAndStartTimeAfter(Theraphist theraphist, LocalDateTime currentTime);
    // TH의 D의 진료 일정 (시간 순)
    // TH의 진료 가능 시작 T
    // TH의 진료 가능 종료 T
    // TH의 CD의 진료 일정 개수
    // TH의 CD기준 다음 진료 T
    // TH의 미승인 예약 목록

    // TH와 P의 D, T에 M과 함께 승인된 예약 저장

}
