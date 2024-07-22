package com.theraconnect.domain.member.mapper;

import com.theraconnect.domain.exercise.entity.GuideVideo;
import com.theraconnect.domain.member.dto.request.TherapistRequestDTO;
import com.theraconnect.domain.member.dto.response.TherapistResponseDTO;
import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.member.entity.UserStatus;
import com.theraconnect.domain.member.entity.WorkDay;
import com.theraconnect.domain.schedule.entity.EmrBase;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;

import java.util.stream.Collectors;

public class TherapistMapper {

    public static Therapist toEntity(TherapistRequestDTO dto) {
        return Therapist.builder()
                .name(dto.getName())
                .loginId(dto.getLoginId())
                .password(dto.getPassword())
                .gender(dto.getGender())
                .profileImagePath(dto.getProfileImagePath())
                .belong(dto.getBelong())
                .userStatus(UserStatus.valueOf(dto.getUserStatus()))
                .workDays(dto.getWorkDay().stream().map(WorkDay::valueOf).collect(Collectors.toList()))
                .career(dto.getCareer())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static TherapistResponseDTO toResponseDTO(Therapist entity) {
        return TherapistResponseDTO.builder()
                .therapistId(entity.getTherapistId())
                .name(entity.getName())
                .loginId(entity.getLoginId())
                .gender(entity.getGender())
                .profileImagePath(entity.getProfileImagePath())
                .belong(entity.getBelong())
                .workDay(entity.getWorkDays().stream().map(WorkDay::name).collect(Collectors.toList()))
                .career(entity.getCareer())
                .phoneNumber(entity.getPhoneNumber())
                .patientIds(entity.getPatients().stream().map(Patient::getPatientId).collect(Collectors.toList()))
                .medicalScheduleIds(entity.getMedicalSchedules().stream().map(MedicalSchedule::getMedicalScheduleId).collect(Collectors.toList()))
                .EMRBaseIds(entity.getEMRBases().stream().map(EmrBase::getEmrBaseId).collect(Collectors.toList()))
                .guideVideoIds(entity.getGuideVideos().stream().map(GuideVideo::getGuideVideoId).collect(Collectors.toList()))
                .build();
    }
}
