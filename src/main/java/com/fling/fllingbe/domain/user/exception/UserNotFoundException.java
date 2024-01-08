package com.fling.fllingbe.domain.user.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
