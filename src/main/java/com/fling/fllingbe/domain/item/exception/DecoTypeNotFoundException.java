package com.fling.fllingbe.domain.item.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class DecoTypeNotFoundException extends ServiceException {
    public DecoTypeNotFoundException() {
        super(ErrorCode.DECO_TYPE_NOT_FOUND);
    }
}