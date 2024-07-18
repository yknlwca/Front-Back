package com.theraconnect.domain.EMR.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EMRRequestDTO {

    private Long patientId;

    private Long theraphistId;

    private String comment;

    private String bodyPart;
}
