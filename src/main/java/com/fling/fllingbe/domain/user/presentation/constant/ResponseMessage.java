package com.fling.fllingbe.domain.user.presentation.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS_LOGIN("로그인에 성공했습니다."),
    SUCCESS_REGISTER("회원가입에 성공했습니다."),
    SUCCESS_REFRESH("토큰 재발급에 성공했습니다."),
    SUCCESS_READ("유저 조회에 성공했습니다."),
    SUCCESS_UPDATE("유저 수정에 성공했습니다."),
    SUCCESS_DELETE("유저 삭제에 성공했습니다.");
    private String message;
}