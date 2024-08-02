package com.theraconnect.domain.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmrBaseResponseDTO {

    private Long EmrBaseId;
    private Float height;
    private Float weight;
    private String surgeryName;
    private LocalDate surgeryDate;
    private String surgeryComponent;
    private Long patientId;
    private Long therapistId;
    private List<Long> EmrDetailIds;
}