package com.theraconnect.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TherapistRequestDTO {

    private String name;

    private String loginId;

    private String password;

    private Integer age;

    private Character gender;

    private String profileImagePath;

    private String belong;

    private String UserStatus;

    private List<String> workDay;

    private String career;

    private String phoneNumber;

}
