package com.theraconnect.domain.EMR.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EMRResponseDTO {

    private Long emrId;

    private LocalDateTime createTime;

    private String comment;

    private String bodyPart;

    private Long patientId;

    private Long theraphistId;
}
