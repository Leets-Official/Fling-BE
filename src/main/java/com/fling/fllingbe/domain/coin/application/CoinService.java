package com.fling.fllingbe.domain.coin.application;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.coin.exception.CoinNotFoundException;
import com.fling.fllingbe.domain.coin.repository.CoinRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import com.fling.fllingbe.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CoinService {
    private final CoinRepository coinRepository;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void updateCoin(String userEmail, Integer newCoinValue) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException());
        Coin coin = coinRepository.findByUser(user).orElseThrow(CoinNotFoundException::new);
        coin.setCoin(newCoinValue + coin.getCoin());
        coinRepository.save(coin);
    }
}
