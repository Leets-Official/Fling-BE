package com.fling.fllingbe.domain.store.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class FlowerNotFoundException extends ServiceException {
    public FlowerNotFoundException() {
        super(ErrorCode.FLOWER_NOT_FOUND);
    }
}