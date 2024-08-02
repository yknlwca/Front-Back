package com.theraconnect.domain.schedule.dto.request;

import com.theraconnect.domain.schedule.entity.ReservationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MedicalScheduleRequestDTO {

    private LocalDateTime reservationTime;
    private String memo;
    private String patientRequest;
    private ReservationStatus reservationStatus;
    private String roomNum;
    private Long patientId;
    private Long therapistId;
    private Long EmrDetailId;
}