package com.fling.fllingbe.domain.user.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class UserConflictException extends ServiceException {
    public UserConflictException() {
        super(ErrorCode.USER_CONFLICT.getHttpStatus(),ErrorCode.USER_CONFLICT.getMessage());
    }
}
