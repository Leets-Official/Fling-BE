package com.fling.fllingbe.domain.item.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_READ_CARD("편지지 조회에 성공했습니다."),
    SUCCESS_READ_CARD_INFO("편지지 정보 조회에 성공했습니다."),
    SUCCESS_ADD_CARD("편지지 추가에 성공했습니다."),

    SUCCESS_READ_DECO("데코 조회에 성공했습니다."),
    SUCCESS_READ_DECO_INFO("데코 정보 조회에 성공했습니다."),
    SUCCESS_ADD_DECO("데코 추가에 성공했습니다."),

    SUCCESS_READ_FLOWER("꽃 조회에 성공했습니다."),
    SUCCESS_READ_FLOWER_INFO("꽃 정보 조회에 성공했습니다."),
    SUCCESS_ADD_FLOWER("꽃 추가에 성공했습니다."),

    SUCCESS_READ_ITEM("아이템 조회에 성공했습니다."),
    SUCCESS_READ_BOUQUET("꽃다발 정보 조회에 성공했습니다.");
    private String message;
}
