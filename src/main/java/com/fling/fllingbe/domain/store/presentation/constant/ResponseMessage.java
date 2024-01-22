package com.fling.fllingbe.domain.store.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_BUY_FLOWER("꽃 구매를 성공했습니다."),
    SUCCESS_BUY_DECO("데코 구매를 성공했습니다."),
    SUCCESS_BUY_CARD("편지지 구매를 성공했습니다."),
    SUCCESS_READ("상점 아이템 조회에 성공했습니다.");
    private String message;
}