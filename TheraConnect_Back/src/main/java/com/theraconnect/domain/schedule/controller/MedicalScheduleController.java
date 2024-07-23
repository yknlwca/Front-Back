package com.theraconnect.domain.schedule.controller;

import com.theraconnect.domain.schedule.dto.request.MedicalScheduleRequestDTO;
import com.theraconnect.domain.schedule.dto.response.MedicalScheduleResponseDTO;
import com.theraconnect.domain.schedule.service.MedicalScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class MedicalScheduleController {

    private final MedicalScheduleService medicalScheduleService;

    // 페이지네이션 조절
    private final String START_PAGE_NO = "0";
    private final String PAGE_SIZE = "10";

    // 보안이 더 중요하다면 spring security
    // 단순함이 좋다면 header에 넣기
    // TODO : spring security 구현 전까지 header로 함
    @GetMapping("/today")
    public ResponseEntity<?> getTodaySchedules(@RequestHeader("Therapist-Id") Long therapistId) {
        List<MedicalScheduleResponseDTO> schedules = medicalScheduleService.getTodaySchedulesByTherapist(therapistId);

        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }

    // SCHEDULE002
    // 예약 변경, 추가인 일정 조회
    @GetMapping("/notification")
    public ResponseEntity<?> getSchedulesByChangedORAdd(@RequestHeader("Therapist-id") Long therapistId) {
        List<MedicalScheduleResponseDTO> scheduleResponseDTOS = medicalScheduleService.getSchedulesByStatusChangeOrAdded(therapistId);
        if (scheduleResponseDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scheduleResponseDTOS);
    }

    // SCHEDULE003
    // 특정 월의 예약된 환자 리스트 조회
    @GetMapping("/therapists/patients")
    public ResponseEntity<?> getConfirmedScheduleByMonth(@RequestHeader("Therapist-id") Long therapistId,
                                                         @RequestParam("year") int year,
                                                         @RequestParam("month") int month) {
        List<MedicalScheduleResponseDTO> scheduleResponseDTOS = medicalScheduleService.getConfirmedSchedulesByTherapistAndMonth(therapistId, year, month);
        if (scheduleResponseDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scheduleResponseDTOS);
    }

    // SCHEDULE004
    // 치료사의 진료 일정 추가
    @PostMapping("/therapists")
    public ResponseEntity<?> addMedicalSchedule(@RequestBody MedicalScheduleRequestDTO medicalScheduleRequestDTO) {
        MedicalScheduleResponseDTO responseDTO = medicalScheduleService.addMedicalSchedule(medicalScheduleRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // SCHEDULE005
    // 치료사의 진료 일정 수정
    @PutMapping("/therapists/{medicalScheduleId}")
    public ResponseEntity<?> updateMedicalSchedule(@PathVariable Long medicalScheduleId, @RequestBody MedicalScheduleRequestDTO medicalScheduleRequestDTO) {
        MedicalScheduleResponseDTO responseDTO = medicalScheduleService.updateMedicalSchedule(medicalScheduleId, medicalScheduleRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // SCHEDULE006
    // 치료사가 선택한 환자의 진료 리스트 조회
    @GetMapping("/therapist/patients/schedules")
    public ResponseEntity<?> getAllSchedulesByTherapistAndPatient(@RequestHeader("Thearpist-Id") Long therapistId,
                                                                  @RequestHeader("Patient-Id") Long patientId) {
        List<MedicalScheduleResponseDTO> scheduleResponseDTOS = medicalScheduleService.getAllSchedulesByTherapistAndPatient(therapistId, patientId);
        if (scheduleResponseDTOS.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(scheduleResponseDTOS);
    }

    // SCHEDULE007
    // 환자의 본인 예약 리스트 조회
    @GetMapping("/patients/not-finished")
    public ResponseEntity<Page<MedicalScheduleResponseDTO>> getAllNotFinishedSchedulesByPatient(
            @RequestHeader("Patient-Id") Long patientId,
            @RequestParam(defaultValue = START_PAGE_NO) int page,
            @RequestParam(defaultValue = PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MedicalScheduleResponseDTO> schedules = medicalScheduleService.getAllNotFinishedScheduleByPatient(patientId, pageable);
        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }

    // SCHEDULE010
    // 환자의 본인 진료 리스트 조회
    @GetMapping("/patients/finished")
    public ResponseEntity<Page<MedicalScheduleResponseDTO>> getAllFinishedSchedulesByPatient(
            @RequestHeader("Patient-Id") Long patientId,
            @RequestParam(defaultValue = START_PAGE_NO) int page,
            @RequestParam(defaultValue = PAGE_SIZE) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<MedicalScheduleResponseDTO> schedules = medicalScheduleService.getAllFinishedScheduleByPatient(patientId, pageable);
        if (schedules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(schedules);
    }
}
