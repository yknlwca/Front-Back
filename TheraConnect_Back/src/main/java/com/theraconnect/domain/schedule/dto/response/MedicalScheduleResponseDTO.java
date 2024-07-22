package com.theraconnect.domain.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalScheduleResponseDTO {

    private Long medicalScheduleId;
    private LocalDateTime reservationTime;
    private String memo;
    private String patientRequest;
    private String reservationStatus;
    private String roomNum;
    private Long patientId;
    private Long therapistId;
    private Long EmrDetailId;
}