package com.theraconnect.domain.schedule.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExerciseScheduleRequestDTO {

    private Long patientId;

    private LocalDateTime exerciseDate;

    private List<Long> patientVideoIds;

    private List<Long> exerciseVideoIds;

}
