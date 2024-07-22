package com.theraconnect.domain.exercise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseReportRequestDTO {

    private String exerciseName;
    private LocalDateTime createTime;
    private String bestVideoPath;
    private String bestImagePatientPath;
    private String bestImageTherapistPath;
    private Integer motionRangeStartPatient;
    private Integer motionRangeStartTherapist;
    private Integer motionRangeEndPatient;
    private Integer motionRangeEndTherapist;
    private String description;
    private Long exerciseResultId;
}