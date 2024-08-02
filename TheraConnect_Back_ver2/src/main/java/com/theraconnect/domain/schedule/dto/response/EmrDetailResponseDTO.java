package com.theraconnect.domain.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmrDetailResponseDTO {

    private Long EmrDetailId;
    private LocalDateTime chartDate;
    private String symptoms;
    private String opinion;
    private Integer bodyComponent;
    private Long EmrBaseId;
    private Long medicalScheduleId;
    private List<Long> exercisePrescriptionIds;
}