package com.fling.fllingbe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestUserRequest {
    private String email;
    private String nickname;
}

