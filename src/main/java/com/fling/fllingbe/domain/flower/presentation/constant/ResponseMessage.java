package com.fling.fllingbe.domain.flower.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_WRITE("편지 작성을 성공했습니다."),
    SUCCESS_READ_SENT_FLOWER("보냈던 꽃 조회에 성공했습니다."),
    SUCCESS_READ_RECEIVED_FLOWER("받았던 꽃 조회에 성공했습니다."),
    SUCCESS_READ_LETTER("편지 조회에 성공했습니다.");
    private String message;
}
