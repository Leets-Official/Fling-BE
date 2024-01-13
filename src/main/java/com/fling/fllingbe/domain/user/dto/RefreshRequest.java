package com.fling.fllingbe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshRequest {
    private String refreshToken;
    private String email;
}

