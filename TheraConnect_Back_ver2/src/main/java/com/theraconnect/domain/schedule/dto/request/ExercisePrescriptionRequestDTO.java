package com.theraconnect.domain.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePrescriptionRequestDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer setCount;
    private Long patientId;
    private Long EmrDetailId;
}