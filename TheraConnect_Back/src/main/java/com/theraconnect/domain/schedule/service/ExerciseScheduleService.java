package com.theraconnect.domain.schedule.service;

import com.theraconnect.domain.schedule.dto.response.ExerciseScheduleResponseDTO;

import java.util.List;

public interface ExerciseScheduleService {

    // 운동 일정 리스트 조회 -> 조건 생각



    // 운동 일정 상세 조회
    ExerciseScheduleResponseDTO getExerciseSchedule(Long id, Long patientId);

}
