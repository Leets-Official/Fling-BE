package com.fling.fllingbe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NicknameRequest {
    private String nickname;
}