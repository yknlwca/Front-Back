package com.theraconnect.domain.schedule.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePrescriptionResponseDTO {

    private Long exercisePrescriptionId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer setCount;
    private Long patientId;
    private Long EmrDetailId;
    private List<Long> guideVideoIds;
}