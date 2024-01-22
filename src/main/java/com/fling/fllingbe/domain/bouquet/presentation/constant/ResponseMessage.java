package com.fling.fllingbe.domain.bouquet.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_CREATE("꽃다발 생성에 성공하였습니다."),
    SUCCESS_READ("꽃다발 조회에 성공하였습니다."),
    SUCCESS_UPDATE("꽃다발 수정에 성공하였습니다.");
    private String message;
}