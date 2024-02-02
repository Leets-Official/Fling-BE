package com.fling.fllingbe.domain.coin.application;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.coin.repository.CoinRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinResetScheduler {

    private final CoinRepository coinRepository;

    public CoinResetScheduler(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Scheduled(cron = "00 17 01 * * ?")
    public void resetHasPickCoinToday() {
        List<Coin> coins = coinRepository.findAll();
        for (Coin coin : coins) {
            coin.setHasPickCoinToday(false);
            coinRepository.save(coin);
        }
    }
}