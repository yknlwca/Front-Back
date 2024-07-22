package com.theraconnect.domain.schedule.mapper;


import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.schedule.dto.request.MedicalScheduleRequestDTO;
import com.theraconnect.domain.schedule.dto.response.MedicalScheduleResponseDTO;
import com.theraconnect.domain.schedule.entity.EmrDetail;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import com.theraconnect.domain.schedule.entity.ReservationStatus;

public class MedicalScheduleMapper {

    public static MedicalSchedule toEntity(MedicalScheduleRequestDTO dto) {
        return MedicalSchedule.builder()
                .reservationTime(dto.getReservationTime())
                .memo(dto.getMemo())
                .patientRequest(dto.getPatientRequest())
                .reservationStatus(ReservationStatus.valueOf(dto.getReservationStatus()))
                .roomNum(dto.getRoomNum())
                .patient(Patient.builder().patientId(dto.getPatientId()).build())
                .therapist(dto.getTherapistId() != null ? Therapist.builder().therapistId(dto.getTherapistId()).build() : null)
                .emrDetail(EmrDetail.builder().emrDetailId(dto.getEmrDetailId()).build())
                .build();
    }

    public static MedicalScheduleResponseDTO toResponseDTO(MedicalSchedule entity) {
        return MedicalScheduleResponseDTO.builder()
                .medicalScheduleId(entity.getMedicalScheduleId())
                .reservationTime(entity.getReservationTime())
                .memo(entity.getMemo())
                .patientRequest(entity.getPatientRequest())
                .reservationStatus(entity.getReservationStatus().name())
                .roomNum(entity.getRoomNum())
                .patientId(entity.getPatient().getPatientId())
                .therapistId(entity.getTherapist() != null ? entity.getTherapist().getTherapistId() : null)
                .EmrDetailId(entity.getEmrDetail().getEmrDetailId())
                .build();
    }
}