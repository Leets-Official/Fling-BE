package com.fling.fllingbe.domain.flower.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class FlowerTypeNotFoundException extends ServiceException {
    public FlowerTypeNotFoundException() {
        super(ErrorCode.FLOWER_TYPE_NOT_FOUND.getHttpStatus(),ErrorCode.FLOWER_TYPE_NOT_FOUND.getMessage());
    }
}