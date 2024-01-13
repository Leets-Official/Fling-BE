package com.fling.fllingbe.domain.user.dto;

import com.fling.fllingbe.global.jwt.presentation.JwtResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID userId;
    private String email;
    private String nickname;
    private JwtResponse token;
//    private String refreshToken;
}