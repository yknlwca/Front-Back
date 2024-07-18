package com.theraconnect.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;
}
