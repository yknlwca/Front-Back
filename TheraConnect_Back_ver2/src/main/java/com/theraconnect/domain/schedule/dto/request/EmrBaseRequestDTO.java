package com.theraconnect.domain.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmrBaseRequestDTO {

    private Float height;
    private Float weight;
    private String surgeryName;
    private LocalDate surgeryDate;
    private String surgeryComponent;
    private Long patientId;
    private Long therapistId;
}