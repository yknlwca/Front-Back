package com.theraconnect.domain.schedule.mapper;


import com.theraconnect.domain.schedule.dto.request.EmrDetailRequestDTO;
import com.theraconnect.domain.schedule.dto.response.EmrDetailResponseDTO;
import com.theraconnect.domain.schedule.entity.EmrBase;
import com.theraconnect.domain.schedule.entity.EmrDetail;
import com.theraconnect.domain.schedule.entity.ExercisePrescription;

import java.util.stream.Collectors;

public class EMRDetailMapper {

    public static EmrDetail toEntity(EmrDetailRequestDTO dto) {
        return EmrDetail.builder()
                .chartDate(dto.getChartDate())
                .symptoms(dto.getSymptoms())
                .opinion(dto.getOpinion())
                .bodyComponent(dto.getBodyComponent())
                .emrBase(EmrBase.builder().emrBaseId(dto.getEmrBaseId()).build())
                .build();
    }

    public static EmrDetailResponseDTO toResponseDTO(EmrDetail entity) {
        return EmrDetailResponseDTO.builder()
                .EmrDetailId(entity.getEmrDetailId())
                .chartDate(entity.getChartDate())
                .symptoms(entity.getSymptoms())
                .opinion(entity.getOpinion())
                .bodyComponent(entity.getBodyComponent())
                .EmrBaseId(entity.getEmrBase().getEmrBaseId())
                .medicalScheduleId(entity.getMedicalSchedule() != null ? entity.getMedicalSchedule().getMedicalScheduleId() : null)
                .exercisePrescriptionIds(entity.getExercisePrescriptions().stream().map(ExercisePrescription::getExercisePrescriptionId).collect(Collectors.toList()))
                .build();
    }
}