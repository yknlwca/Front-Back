package com.theraconnect.domain.schedule.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExerciseScheduleResponseDTO {

    private Long exerciseScheduleId;

    private Long patientId;

    private LocalDateTime exerciseDate;

    private List<Long> patientVideoIds;

    private List<Long> exerciseVideoIds;
}
