package com.theraconnect.domain.schedule.service;


import com.theraconnect.domain.schedule.dto.request.MedicalScheduleRequestDTO;
import com.theraconnect.domain.schedule.dto.response.MedicalScheduleResponseDTO;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import com.theraconnect.domain.schedule.entity.ReservationStatus;
import com.theraconnect.domain.schedule.mapper.MedicalScheduleMapper;
import com.theraconnect.domain.schedule.repository.MedicalScheduleRepository;
import com.theraconnect.global.exception.CustomException;
import com.theraconnect.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalScheduleServiceImpl implements MedicalScheduleService {

    private final MedicalScheduleRepository medicalScheduleRepository;

    // 특정 치료사의 당일 진료 일정
    @Override
    public List<MedicalScheduleResponseDTO> getTodaySchedulesByTherapist(Long therapist) {
        // 오늘 00:00:00
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        // 오늘 23:59:59
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        List<MedicalSchedule> schedules = medicalScheduleRepository.findAllByTherapistAndToday(therapist, startOfDay, endOfDay);
        return MedicalScheduleMapper.toResponseDTOList(schedules);
    }

    // 특정 치료사의 예약 변경, 추가 조회
    @Override
    public List<MedicalScheduleResponseDTO> getSchedulesByStatusChangeOrAdded(Long therapistId) {
        List<MedicalSchedule> schedules = medicalScheduleRepository.findAllByTherapistAndStatusChangeOrAdd(therapistId);
        return MedicalScheduleMapper.toResponseDTOList(schedules);
    }


    // 특정 월의 특정 치료사의 진료 일정(예약 확정)
    @Override
    public List<MedicalScheduleResponseDTO> getConfirmedSchedulesByTherapistAndMonth(Long therapistId, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        // 1일
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        // 해당 달의 마지막 날 24시
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atTime(23, 59, 59);
        List<MedicalSchedule> schedules = medicalScheduleRepository.findConfirmedByTherapistAndMonth(therapistId, startOfMonth, endOfMonth);
        return MedicalScheduleMapper.toResponseDTOList(schedules);
    }

    // 치료사의 진료 일정 추가
    @Override
    public MedicalScheduleResponseDTO addMedicalSchedule(MedicalScheduleRequestDTO medicalScheduleRequestDTO) {
        MedicalSchedule medicalSchedule = MedicalScheduleMapper.toEntity(medicalScheduleRequestDTO);
        MedicalSchedule saved = medicalScheduleRepository.save(medicalSchedule);
        return MedicalScheduleMapper.toResponseDTO(saved);
    }

    // 치료사의 진료 일정 수정
    @Override
    @Transactional
    public MedicalScheduleResponseDTO updateMedicalSchedule(Long medicalScheduleId, MedicalScheduleRequestDTO medicalScheduleRequestDTO) {
        Optional<MedicalSchedule> optionalMedicalSchedule = medicalScheduleRepository.findById(medicalScheduleId);
        if (optionalMedicalSchedule.isPresent()) {
            MedicalSchedule medicalSchedule = optionalMedicalSchedule.get();
            medicalSchedule.setReservationTime(medicalScheduleRequestDTO.getReservationTime());
            medicalSchedule.setMemo(medicalScheduleRequestDTO.getMemo());
            medicalSchedule.setReservationStatus(ReservationStatus.CONFIRMED);
            return MedicalScheduleMapper.toResponseDTO(medicalSchedule);
        } else {
            throw new CustomException(ErrorCode.INVALID_MEDICALSCHEDULE_ID);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalScheduleResponseDTO> getAllSchedulesByTherapistAndPatient(Long therapistId, Long patientId) {
        List<MedicalSchedule> schedules = medicalScheduleRepository.findAllByTherapistAndPatient(therapistId, patientId);
        return MedicalScheduleMapper.toResponseDTOList(schedules);
    }


    // 환자 본인의 예약 기록 조회
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalScheduleResponseDTO> getAllNotFinishedScheduleByPatient(Long patientId, Pageable pageable) {
        Page<MedicalSchedule> schedules = medicalScheduleRepository.findAllByPatientAndNotFinished(patientId, pageable);
        return schedules.map(MedicalScheduleMapper::toResponseDTO);
    }

    // 환자 본인의 진료 기록 조회
    @Override
    @Transactional(readOnly = true)
    public Page<MedicalScheduleResponseDTO> getAllFinishedScheduleByPatient(Long patientId, Pageable pageable) {
        Page<MedicalSchedule> schedules = medicalScheduleRepository.findAllByPatientAndFinished(patientId, pageable);
        return schedules.map(MedicalScheduleMapper::toResponseDTO);
    }

}
