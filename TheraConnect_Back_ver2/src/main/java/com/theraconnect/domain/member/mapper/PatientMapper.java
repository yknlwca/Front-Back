package com.theraconnect.domain.member.mapper;

import com.theraconnect.domain.exercise.entity.ExerciseResult;
import com.theraconnect.domain.member.dto.request.PatientRequestDTO;
import com.theraconnect.domain.member.dto.response.PatientResponseDTO;
import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.member.entity.UserStatus;
import com.theraconnect.domain.schedule.entity.EmrBase;
import com.theraconnect.domain.schedule.entity.ExercisePrescription;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;

import java.util.stream.Collectors;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto) {
        return Patient.builder()
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .name(dto.getName())
                .gender(dto.getGender())
                .birthday(dto.getBirthday())
                .phoneNumber(dto.getPhoneNumber())
                .provider(dto.getProvider())
                .accountId(dto.getAccountId())
                .profileImagePath(dto.getProfileImagePath())
                .userStatus(UserStatus.valueOf(dto.getUserStatus()))
                .therapist(Therapist.builder().therapistId(dto.getTherapistId()).build())
                .build();
    }

    public static PatientResponseDTO toResponseDTO(Patient entity) {
        return PatientResponseDTO.builder()
                .patientId(entity.getPatientId())
                .loginId(entity.getLoginId())
                .name(entity.getName())
                .gender(entity.getGender())
                .birthday(entity.getBirthday())
                .phoneNumber(entity.getPhoneNumber())
                .provider(entity.getProvider())
                .accountId(entity.getAccountId())
                .profileImagePath(entity.getProfileImagePath())
                .therapistId(entity.getTherapist().getTherapistId())
                .EMRBaseIds(entity.getEMRBases().stream().map(EmrBase::getEmrBaseId).collect(Collectors.toList()))
                .medicalScheduleIds(entity.getMedicalSchedules().stream().map(MedicalSchedule::getMedicalScheduleId).collect(Collectors.toList()))
                .exercisePrescriptionIds(entity.getExercisePrescriptions().stream().map(ExercisePrescription::getExercisePrescriptionId).collect(Collectors.toList()))
                .exerciseResultIds(entity.getExerciseResults().stream().map(ExerciseResult::getExerciseResultId).collect(Collectors.toList()))
                .build();
    }
}
