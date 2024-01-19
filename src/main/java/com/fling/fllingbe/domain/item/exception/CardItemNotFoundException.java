package com.fling.fllingbe.domain.item.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class CardItemNotFoundException extends ServiceException {
    public CardItemNotFoundException() {
        super(ErrorCode.CARD_ITEM_NOT_FOUND);
    }
}