package com.theraconnect.domain.schedule.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmrDetailRequestDTO {

    private LocalDateTime chartDate;
    private String symptoms;
    private String opinion;
    private Integer bodyComponent;
    private Long EmrBaseId;
}