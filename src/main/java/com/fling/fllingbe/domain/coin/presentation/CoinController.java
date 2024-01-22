package com.fling.fllingbe.domain.coin.presentation;

import com.fling.fllingbe.domain.coin.application.CoinService;
import com.fling.fllingbe.domain.coin.dto.CoinRequest;
import com.fling.fllingbe.global.dto.ResponseDto;
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

import static com.fling.fllingbe.domain.coin.presentation.constant.ResponseMessage.SUCCESS_UPDATE;
import static com.fling.fllingbe.domain.store.presentation.constant.ResponseMessage.SUCCESS_BUY_CARD;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coin")
public class CoinController {
    private final CoinService coinService;

    @PostMapping()
    public ResponseDto updateCoin(Authentication authentication, @RequestBody CoinRequest coinRequest) {
        coinService.updateCoin(authentication.getName(), coinRequest.getCoin());
        return ResponseDto.of(OK.value(), SUCCESS_UPDATE.getMessage());
    }
}