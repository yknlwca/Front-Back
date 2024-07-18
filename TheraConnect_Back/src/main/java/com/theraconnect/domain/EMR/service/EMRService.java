package com.theraconnect.domain.EMR.service;

import com.theraconnect.domain.EMR.dto.request.EMRRequestDTO;
import com.theraconnect.domain.EMR.dto.response.EMRResponseDTO;

public interface EMRService {

    // EMR 상세 보기
    EMRResponseDTO getEMR(Long EMRId);

    // EMR 작성
    EMRResponseDTO createEMR(EMRRequestDTO emrRequestDTO);

    // EMR 수정

    // EMR 삭제
    void deleteEMR(Long EMRId);
}
