package com.theraconnect.domain.schedule.dto.response;

import com.theraconnect.domain.schedule.entity.ReservationStatus;
import lombok.*;

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
    private ReservationStatus reservationStatus;
    private String roomNum;
    private Long patientId;
    private Long therapistId;
    private Long EmrDetailId;
}