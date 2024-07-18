package com.theraconnect.domain.EMR.service;

import com.theraconnect.domain.EMR.dto.request.EMRRequestDTO;
import com.theraconnect.domain.EMR.dto.response.EMRResponseDTO;
import com.theraconnect.domain.EMR.entity.EMR;
import com.theraconnect.domain.EMR.repository.EMRRepository;
import com.theraconnect.domain.member.entity.Patient;
import com.theraconnect.domain.member.entity.Theraphist;
import com.theraconnect.domain.member.repository.PatientRepository;
import com.theraconnect.domain.member.repository.TheraphistRepository;
import com.theraconnect.global.exception.CustomException;
import com.theraconnect.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class EMRServiceImpl implements EMRService {

    private final EMRRepository emrRepository;
    private final PatientRepository patientRepository;
    private final TheraphistRepository theraphistRepository;

    @Override
    public EMRResponseDTO getEMR(Long EMRId) {
        EMR emr = emrRepository.findById(EMRId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMR_ID));
        return convertToDTO(emr);
    }

    @Override
    public EMRResponseDTO createEMR(EMRRequestDTO emrRequestDTO) {
        EMR emr = convertToEntity(emrRequestDTO);
        emrRepository.save(emr);
        return convertToDTO(emr);
    }

    @Override
    @Transient
    public void deleteEMR(Long EMRId) {
        EMR emr = emrRepository.findById(EMRId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMR_ID));
        emrRepository.delete(emr);
    }

    private EMRResponseDTO convertToDTO(EMR emr) {
        return EMRResponseDTO.builder()
                .emrId(emr.getEmrId())
                .createTime(emr.getCreateTime())
                .comment(emr.getComment())
                .bodyPart(emr.getBodyPart())
                .patientId(emr.getPatient().getPatientId())
                .theraphistId(emr.getTheraphist().getTheraphistId())
                .build();
    }

    private EMR convertToEntity(EMRRequestDTO emrRequestDTO) {
        Patient patient = patientRepository.findById(emrRequestDTO.getPatientId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_PATIENT_ID));

        Theraphist theraphist = theraphistRepository.findById(emrRequestDTO.getTheraphistId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_THERAPHIST_ID));

        return EMR.builder()
                .patient(patient)
                .theraphist(theraphist)
                .comment(emrRequestDTO.getComment())
                .bodyPart(emrRequestDTO.getBodyPart())
                .build();
    }
}
