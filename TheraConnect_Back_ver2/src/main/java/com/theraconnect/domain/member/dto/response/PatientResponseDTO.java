package com.theraconnect.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDTO {

    private Long patientId;

    private String loginId;

    private String name;

    private Character gender;

    private LocalDate birthday;

    private String phoneNumber;

    private String provider;

    private String accountId;

    private String profileImagePath;

    private Long therapistId;

    private List<Long> EMRBaseIds;

    private List<Long> medicalScheduleIds;

    private List<Long> exercisePrescriptionIds;

    private List<Long> exerciseResultIds;

}
