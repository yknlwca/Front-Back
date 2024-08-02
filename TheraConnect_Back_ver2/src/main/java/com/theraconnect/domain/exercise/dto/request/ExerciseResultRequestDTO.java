package com.theraconnect.domain.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseResultRequestDTO {

    private LocalDate exerciseDate;
    private Long patientId;
}