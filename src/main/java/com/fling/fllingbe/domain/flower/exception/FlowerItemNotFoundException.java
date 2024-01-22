package com.fling.fllingbe.domain.flower.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class FlowerItemNotFoundException extends ServiceException {
    public FlowerItemNotFoundException() {
        super(ErrorCode.FLOWER_ITEM_NOT_FOUND.getHttpStatus(),ErrorCode.FLOWER_ITEM_NOT_FOUND.getMessage());
    }
}