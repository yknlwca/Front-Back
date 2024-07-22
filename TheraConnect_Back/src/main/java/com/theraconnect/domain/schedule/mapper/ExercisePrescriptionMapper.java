package com.theraconnect.domain.schedule.mapper;


import com.theraconnect.domain.exercise.entity.GuideVideo;
import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.schedule.dto.request.ExercisePrescriptionRequestDTO;
import com.theraconnect.domain.schedule.dto.response.ExercisePrescriptionResponseDTO;
import com.theraconnect.domain.schedule.entity.EmrDetail;
import com.theraconnect.domain.schedule.entity.ExercisePrescription;

import java.util.stream.Collectors;

public class ExercisePrescriptionMapper {

    public static ExercisePrescription toEntity(ExercisePrescriptionRequestDTO dto) {
        return ExercisePrescription.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .setCount(dto.getSetCount())
                .patient(Patient.builder().patientId(dto.getPatientId()).build())
                .emrDetail(dto.getEmrDetailId() != null ? EmrDetail.builder().emrDetailId(dto.getEmrDetailId()).build() : null)
                .build();
    }

    public static ExercisePrescriptionResponseDTO toResponseDTO(ExercisePrescription entity) {
        return ExercisePrescriptionResponseDTO.builder()
                .exercisePrescriptionId(entity.getExercisePrescriptionId())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .setCount(entity.getSetCount())
                .patientId(entity.getPatient().getPatientId())
                .EmrDetailId(entity.getEmrDetail() != null ? entity.getEmrDetail().getEmrDetailId() : null)
                .guideVideoIds(entity.getGuideVideoList().stream().map(GuideVideo::getGuideVideoId).collect(Collectors.toList()))
                .build();
    }
}