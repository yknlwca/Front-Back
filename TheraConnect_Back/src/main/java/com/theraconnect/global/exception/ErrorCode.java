package com.theraconnect.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_MEDICALSCHEDULE_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 진료 ID입니다."),
    ;
    private final HttpStatus httpStatus;
    private final String message;

}
