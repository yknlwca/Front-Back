package com.theraconnect.domain.schedule.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalScheduleRequestDTO {

    private Long patientId;

    private Long theraphistId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
