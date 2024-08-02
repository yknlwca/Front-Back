package com.theraconnect.domain.exercise.mapper;

import com.theraconnect.domain.exercise.dto.request.ExerciseReportRequestDTO;
import com.theraconnect.domain.exercise.dto.response.ExerciseReportResponseDTO;
import com.theraconnect.domain.exercise.entity.ExerciseReport;
import com.theraconnect.domain.exercise.entity.ExerciseResult;

public class ExerciseReportMapper {

    public static ExerciseReport toEntity(ExerciseReportRequestDTO dto) {
        return ExerciseReport.builder()
                .exerciseName(dto.getExerciseName())
                .createTime(dto.getCreateTime())
                .bestVideoPath(dto.getBestVideoPath())
                .bestImagePatientPath(dto.getBestImagePatientPath())
                .bestImageTherapistPath(dto.getBestImageTherapistPath())
                .motionRangeStartPatient(dto.getMotionRangeStartPatient())
                .motionRangeStartTherapist(dto.getMotionRangeStartTherapist())
                .motionRangeEndPatient(dto.getMotionRangeEndPatient())
                .motionRangeEndTherapist(dto.getMotionRangeEndTherapist())
                .description(dto.getDescription())
                .exerciseResult(ExerciseResult.builder().exerciseResultId(dto.getExerciseResultId()).build())
                .build();
    }

    public static ExerciseReportResponseDTO toResponseDTO(ExerciseReport entity) {
        return ExerciseReportResponseDTO.builder()
                .exerciseReportId(entity.getExerciseReportId())
                .exerciseName(entity.getExerciseName())
                .createTime(entity.getCreateTime())
                .bestVideoPath(entity.getBestVideoPath())
                .bestImagePatientPath(entity.getBestImagePatientPath())
                .bestImageTherapistPath(entity.getBestImageTherapistPath())
                .motionRangeStartPatient(entity.getMotionRangeStartPatient())
                .motionRangeStartTherapist(entity.getMotionRangeStartTherapist())
                .motionRangeEndPatient(entity.getMotionRangeEndPatient())
                .motionRangeEndTherapist(entity.getMotionRangeEndTherapist())
                .description(entity.getDescription())
                .exerciseResultId(entity.getExerciseResult() != null ? entity.getExerciseResult().getExerciseResultId() : null)
                .build();
    }
}