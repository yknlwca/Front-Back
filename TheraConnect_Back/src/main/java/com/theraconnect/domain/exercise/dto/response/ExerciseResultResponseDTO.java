package com.theraconnect.domain.exercise.dto.response;

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
public class ExerciseResultResponseDTO {

    private Long exerciseResultId;
    private LocalDate exerciseDate;
    private Long patientId;
    private List<Long> guideVideoIds;
    private List<Long> exerciseVideoIds;
    private List<Long> exerciseReportIds;
}