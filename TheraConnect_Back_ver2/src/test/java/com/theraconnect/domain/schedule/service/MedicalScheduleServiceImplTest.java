package com.theraconnect.domain.schedule.service;

import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Therapist;
import com.theraconnect.domain.schedule.dto.response.MedicalScheduleResponseDTO;
import com.theraconnect.domain.schedule.entity.EmrDetail;
import com.theraconnect.domain.schedule.entity.MedicalSchedule;
import com.theraconnect.domain.schedule.entity.ReservationStatus;
import com.theraconnect.domain.schedule.repository.MedicalScheduleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MedicalScheduleServiceImplTest {

    @Mock
    private MedicalScheduleRepository medicalScheduleRepository;

    @InjectMocks
    private MedicalScheduleServiceImpl medicalScheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    // 진료 없는 날
    @Test
    void getTodaySchedulesByTherapist_shouldReturnEmptyListWhenNoSchedules() {
        Long therapistId = 1L;
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);

        when(medicalScheduleRepository.findAllByTherapistAndToday(therapistId, startOfDay, endOfDay))
                .thenReturn(Collections.emptyList());

        List<MedicalScheduleResponseDTO> result = medicalScheduleService.getTodaySchedulesByTherapist(therapistId);
        assertEquals(0, result.size());
    }

    // 진료 있는 날
    @Test
    void getTodaySchedulesByTherapist_shouldReturnSchedulesWhenExists() {
        Long therapistId = 1L;
        LocalDateTime startOfDay = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.now().with(LocalTime.MAX);
        MedicalSchedule schedule = MedicalSchedule.builder()
                .medicalScheduleId(1L)
                .reservationTime(LocalDateTime.now())
                .memo("Memo")
                .patientRequest("Request")
                .reservationStatus(ReservationStatus.CONFIRMED)
                .roomNum("101")
                .patient(Patient.builder().patientId(1L).build())
                .therapist(Therapist.builder().therapistId(therapistId).build())
                .emrDetail(EmrDetail.builder().emrDetailId(1L).build())
                .build();

        when(medicalScheduleRepository.findAllByTherapistAndToday(therapistId, startOfDay, endOfDay))
                .thenReturn(Collections.singletonList(schedule));

        List<MedicalScheduleResponseDTO> result = medicalScheduleService.getTodaySchedulesByTherapist(therapistId);
        assertEquals(1, result.size());
        assertEquals("Memo", result.get(0).getMemo());
    }
}