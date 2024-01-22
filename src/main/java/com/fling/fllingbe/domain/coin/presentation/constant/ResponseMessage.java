package com.fling.fllingbe.domain.coin.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_UPDATE("코인 수정을 성공했습니다.");
    private String message;
}
