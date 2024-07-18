package com.theraconnect.domain.schedule.service;

import com.theraconnect.domain.schedule.dto.response.ExerciseScheduleResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseScheduleServiceImpl implements ExerciseScheduleService {
    @Override
    public ExerciseScheduleResponseDTO getExerciseSchedule(Long id, Long patientId) {
        return null;
    }
}
