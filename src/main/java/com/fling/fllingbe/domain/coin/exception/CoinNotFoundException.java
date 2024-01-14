package com.fling.fllingbe.domain.coin.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class CoinNotFoundException extends ServiceException {
    public CoinNotFoundException() {
        super(ErrorCode.COIN_NOT_FOUND);
    }
}