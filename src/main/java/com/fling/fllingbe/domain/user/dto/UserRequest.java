package com.fling.fllingbe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRequest {
    private String accessToken;
    private String nickname;
}

