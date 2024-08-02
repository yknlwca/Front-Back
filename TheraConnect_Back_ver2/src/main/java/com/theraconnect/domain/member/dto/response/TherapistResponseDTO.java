package com.theraconnect.domain.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TherapistResponseDTO {

    private Long therapistId;

    private String name;

    private String loginId;

    private Character gender;

    private String profileImagePath;

    private String belong;

    private List<String> workDay;

    private String career;

    private String phoneNumber;

    private List<Long> patientIDs;

    private List<Long> patientIds;

    private List<Long> medicalScheduleIds;

    private List<Long> EMRBaseIds;

    private List<Long> guideVideoIds;

}
