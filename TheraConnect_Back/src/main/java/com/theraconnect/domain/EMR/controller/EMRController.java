package com.theraconnect.domain.EMR.controller;

import com.theraconnect.domain.EMR.dto.request.EMRRequestDTO;
import com.theraconnect.domain.EMR.dto.response.EMRResponseDTO;
import com.theraconnect.domain.EMR.repository.EMRRepository;
import com.theraconnect.domain.EMR.service.EMRService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emr")
@RequiredArgsConstructor
public class EMRController {

    private final EMRService emrService;

    // emr 작성
    @PostMapping("")
    public ResponseEntity<?> createEMR(@RequestBody EMRRequestDTO emrRequestDTO) {
        EMRResponseDTO emrResponseDTO = emrService.createEMR(emrRequestDTO);
        return new ResponseEntity<>(emrResponseDTO, HttpStatus.CREATED);
    }

    // emr 조회
    @GetMapping("/{emrId}")
    public ResponseEntity<?> getEMR(@PathVariable Long emrId) {
        EMRResponseDTO emrResponseDTO = emrService.getEMR(emrId);
        return ResponseEntity.ok(emrResponseDTO);
    }

    // emr 삭제
    @DeleteMapping("/{emrId}")
    public ResponseEntity<?> deleteEMR(@PathVariable Long emrId) {
        emrService.deleteEMR(emrId);
        return ResponseEntity.noContent().build();
    }
}
