package com.theraconnect.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {

    private String LoginId;

    private String password;

    private String name;

    private Character gender;

    private LocalDate birthday;

    private String phoneNumber;

    private String provider;

    private String accountId;

    private String profileImagePath;

    private String userStatus;

    private Long therapistId;

}
