package com.fling.fllingbe.domain.item.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class DecoItemNotFoundException extends ServiceException {
    public DecoItemNotFoundException() {
        super(ErrorCode.DECO_ITEM_NOT_FOUND.getHttpStatus(),ErrorCode.DECO_ITEM_NOT_FOUND.getMessage());
    }
}