package com.fling.fllingbe.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(403, "EXPIRED_TOKEN", "만료된 토큰입니다."),
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "유저를 찾을 수 없습니다."),
    COIN_NOT_FOUND(404, "COIN_NOT_FOUND", "코인을 찾을 수 없습니다."),
    FLOWER_ITEM_NOT_FOUND(404, "FLOWER_ITEM_NOT_FOUND", "꽃을 찾을 수 없습니다."),
    FLOWER_TYPE_NOT_FOUND(404, "FLOWER_TYPE_NOT_FOUND", "꽃 종류를 찾을 수 없습니다."),
    INSUFFICIENT_COIN_BALANCE(400, "INSUFFICIENT_COIN_BALANCE", "코인 잔액이 부족합니다."),
    DECO_TYPE_NOT_FOUND(404, "DECO_TYPE_NOT_FOUND", "데코 타입을 찾을 수 없습니다."),
    DECO_ITEM_NOT_FOUND(404, "DECO_ITEM_NOT_FOUND", "데코 아이템을 찾을 수 없습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}