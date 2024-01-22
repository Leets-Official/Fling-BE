package com.fling.fllingbe.domain.item.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class CardTypeNotFoundException extends ServiceException {
    public CardTypeNotFoundException() {
        super(ErrorCode.CARD_TYPE_NOT_FOUND.getHttpStatus(),ErrorCode.CARD_TYPE_NOT_FOUND.getMessage());
    }
}