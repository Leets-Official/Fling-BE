package com.fling.fllingbe.global.jwt.exception;

import com.fling.fllingbe.global.error.exception.ServiceException;
import com.fling.fllingbe.global.error.ErrorCode;

public class InvalidTokenException extends ServiceException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}