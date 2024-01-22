package com.fling.fllingbe.global.error.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final int errorCode;
    protected ServiceException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}