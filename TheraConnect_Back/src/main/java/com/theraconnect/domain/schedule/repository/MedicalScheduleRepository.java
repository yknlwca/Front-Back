package com.theraconnect.domain.schedule.repository;

import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalScheduleRepository extends JpaRepository<MedicalSchedule, Long> {

    // 치료사의 당일 진료 일정
    @Query("SELECT ms FROM MedicalSchedule ms WHERE ms.therapist.therapistId = :therapistId AND ms.reservationTime BETWEEN :startOfDay AND :endOfDay")
    List<MedicalSchedule> findAllByTherapistAndToday(@Param("therapistId") Long therapistId,
                                                     @Param("startOfDay")LocalDateTime startOfDay,
                                                     @Param("endOfDay") LocalDateTime endOfDay);

    // 일정 상태가 변경, 추가인 리스트 조회
    @Query("SELECT ms FROM  MedicalSchedule ms WHERE ms.therapist.therapistId = :therapistId AND ms.reservationStatus IN ('CHANGED','WAITING')")
    List<MedicalSchedule> findAllByTherapistAndStatusChangeOrAdd(@Param("therapistId") Long therapistId);

    // 특정 치료사의 특정 월 예약 확정된 환자 리스트 조회
    @Query("SELECT ms FROM MedicalSchedule ms WHERE ms.therapist.therapistId = :therapistId AND ms.reservationStatus = 'CONFIRMED' AND ms.reservationTime BETWEEN :startOfMonth AND :endOfMonth")
    List<MedicalSchedule> findConfirmedByTherapistAndMonth(@Param("therapistId") Long therapistId,
                                                           @Param("startOfMonth") LocalDateTime startOfMonth,
                                                           @Param("endOfMonth") LocalDateTime endOfMonth);

    // 치료사가 선택한 환자의 진료 일정 리스트 조회
    @Query("SELECT ms FROM MedicalSchedule ms WHERE ms.therapist.therapistId = :therapistId AND ms.patient.patientId = :patientId")
    List<MedicalSchedule> findAllByTherapistAndPatient(@Param("therapistId") Long therapistId, @Param("patientId") Long patientId);

    // 환자의 예약 기록 조회
    @Query("SELECT ms FROM MedicalSchedule ms WHERE ms.patient.patientId = :patientId AND ms.reservationStatus != 'FINISHED' ORDER BY ms.reservationTime ASC")
    Page<MedicalSchedule> findAllByPatientAndNotFinished(@Param("patientId") Long patientId, Pageable pageable);

    // 환자의 진료 기록 조회
    @Query("SELECT ms FROM MedicalSchedule ms WHERE ms.patient.patientId = :patientId AND ms.reservationStatus = 'FINISHED' ORDER BY ms.reservationTime ASC")
    Page<MedicalSchedule> findAllByPatientAndFinished(@Param("patientId") Long patientId, Pageable pageable);
}
