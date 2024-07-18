package com.theraconnect.global.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    //TODO: 수정이 필요함

    // EMR 에러
    INVALID_EMR_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 EMR ID"),

    // Patient 에러
    INVALID_PATIENT_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 Patient ID"),


    // Theraphist 에러
    INVALID_THERAPHIST_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 Theraphist ID"),
    ;
    private final HttpStatus httpStatus;
    private final String message;

}