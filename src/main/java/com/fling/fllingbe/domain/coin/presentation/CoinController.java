package com.fling.fllingbe.domain.coin.presentation;

import com.fling.fllingbe.domain.coin.application.CoinService;
import com.fling.fllingbe.domain.coin.dto.CoinRequest;
import com.fling.fllingbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coin")
public class CoinController {
    private final CoinService coinService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> updateCoin(Authentication authentication, @RequestBody CoinRequest coinRequest) {
        coinService.updateCoin(authentication.getName(), coinRequest.getCoin());
        return new ResponseEntity<>(Map.of("message", "코인 업데이트가 성공했습x니다."), HttpStatus.OK);
    }
}
