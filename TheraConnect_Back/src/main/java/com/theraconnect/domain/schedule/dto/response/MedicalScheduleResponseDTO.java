package com.theraconnect.domain.schedule.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalScheduleResponseDTO {

    private Long medicalScheduleId;

    private Long patientId;

    private Long therapistId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
