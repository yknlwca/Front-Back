package com.theraconnect.domain.exercise.mapper;

import com.theraconnect.domain.exercise.dto.request.ExerciseResultRequestDTO;
import com.theraconnect.domain.exercise.dto.response.ExerciseResultResponseDTO;
import com.theraconnect.domain.exercise.entity.ExerciseReport;
import com.theraconnect.domain.exercise.entity.ExerciseResult;
import com.theraconnect.domain.exercise.entity.ExerciseVideo;
import com.theraconnect.domain.exercise.entity.GuideVideo;
import com.theraconnect.domain.member.entity.Patient;

import java.util.stream.Collectors;

public class ExerciseResultMapper {

    public static ExerciseResult toEntity(ExerciseResultRequestDTO dto) {
        return ExerciseResult.builder()
                .exerciseDate(dto.getExerciseDate())
                .patient(Patient.builder().patientId(dto.getPatientId()).build())
                .build();
    }

    public static ExerciseResultResponseDTO toResponseDTO(ExerciseResult entity) {
        return ExerciseResultResponseDTO.builder()
                .exerciseResultId(entity.getExerciseResultId())
                .exerciseDate(entity.getExerciseDate())
                .patientId(entity.getPatient().getPatientId())
                .guideVideoIds(entity.getGuideVideos().stream().map(GuideVideo::getGuideVideoId).collect(Collectors.toList()))
                .exerciseVideoIds(entity.getExerciseVideos().stream().map(ExerciseVideo::getExerciseVideoId).collect(Collectors.toList()))
                .exerciseReportIds(entity.getExerciseReports().stream().map(ExerciseReport::getExerciseReportId).collect(Collectors.toList()))
                .build();
    }
}