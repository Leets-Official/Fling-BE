package com.fling.fllingbe.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private UUID userId;
    private String email;
    private String nickname;
    private String dDay;
    private String description;
    private Integer coin;
    private boolean isCoinAlreadyDrawn;
}
