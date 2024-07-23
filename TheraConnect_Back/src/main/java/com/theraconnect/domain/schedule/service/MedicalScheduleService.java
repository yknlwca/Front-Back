package com.theraconnect.domain.schedule.service;


import com.theraconnect.domain.schedule.dto.request.MedicalScheduleRequestDTO;
import com.theraconnect.domain.schedule.dto.response.MedicalScheduleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicalScheduleService {

    // 특정 치료사의 당일 진료 일정
    List<MedicalScheduleResponseDTO> getTodaySchedulesByTherapist(Long therapist);

    // 특정 치료사의 예약 변경, 추가 리스트 조회
    List<MedicalScheduleResponseDTO> getSchedulesByStatusChangeOrAdded(Long therapistId);

    // 특정 치료사의 특정 월 예약 확정된 환자 리스트 조회
    List<MedicalScheduleResponseDTO> getConfirmedSchedulesByTherapistAndMonth(Long therapistId, int year, int month);

    // 치료사의 환자 진료 일정 추가
    MedicalScheduleResponseDTO addMedicalSchedule(MedicalScheduleRequestDTO medicalScheduleRequestDTO);

    // 치료사의 환자 진료 일정 수정
    MedicalScheduleResponseDTO updateMedicalSchedule(Long MedicalScheduleId, MedicalScheduleRequestDTO medicalScheduleRequestDTO);

    // 치료사가 선택한 환자의 진료 일정 리스트 조회
    List<MedicalScheduleResponseDTO> getAllSchedulesByTherapistAndPatient(Long therapistId, Long patientId);

    // 환자의 본인 예약 기록 보기
    Page<MedicalScheduleResponseDTO> getAllNotFinishedScheduleByPatient(Long patientId, Pageable pageable);

    // 환자의 본인 진료 기록 보기
    Page<MedicalScheduleResponseDTO> getAllFinishedScheduleByPatient(Long patientId, Pageable pageable);
}
