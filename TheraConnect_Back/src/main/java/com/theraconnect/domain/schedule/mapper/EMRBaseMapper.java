package com.theraconnect.domain.schedule.mapper;

import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.schedule.dto.request.EmrBaseRequestDTO;
import com.theraconnect.domain.schedule.dto.response.EmrBaseResponseDTO;
import com.theraconnect.domain.schedule.entity.EmrBase;
import com.theraconnect.domain.schedule.entity.EmrDetail;

import java.util.stream.Collectors;

public class EMRBaseMapper {

    public static EmrBase toEntity(EmrBaseRequestDTO dto) {
        return EmrBase.builder()
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .surgeryName(dto.getSurgeryName())
                .surgeryDate(dto.getSurgeryDate())
                .surgeryComponent(dto.getSurgeryComponent())
                .patient(Patient.builder().patientId(dto.getPatientId()).build())
                .therapist(Therapist.builder().therapistId(dto.getTherapistId()).build())
                .build();
    }

    public static EmrBaseResponseDTO toResponseDTO(EmrBase entity) {
        return EmrBaseResponseDTO.builder()
                .EmrBaseId(entity.getEmrBaseId())
                .height(entity.getHeight())
                .weight(entity.getWeight())
                .surgeryName(entity.getSurgeryName())
                .surgeryDate(entity.getSurgeryDate())
                .surgeryComponent(entity.getSurgeryComponent())
                .patientId(entity.getPatient().getPatientId())
                .therapistId(entity.getTherapist().getTherapistId())
                .EmrDetailIds(entity.getEmrDetails().stream().map(EmrDetail::getEmrDetailId).collect(Collectors.toList()))
                .build();
    }
}