package com.fling.fllingbe.global.jwt.exception;

import com.fling.fllingbe.global.error.exception.ServiceException;
import com.fling.fllingbe.global.error.ErrorCode;

public class ExpiredTokenException extends ServiceException {
    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}