package com.fling.fllingbe.domain.coin.exception;

import com.fling.fllingbe.global.error.ErrorCode;
import com.fling.fllingbe.global.error.exception.ServiceException;

public class InsufficientCoinBalanceException extends ServiceException {
    public InsufficientCoinBalanceException() {
        super(ErrorCode.INSUFFICIENT_COIN_BALANCE);
    }
}