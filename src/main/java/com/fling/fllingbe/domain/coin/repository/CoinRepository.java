package com.fling.fllingbe.domain.coin.repository;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoinRepository extends JpaRepository<Coin, UUID> {
    Optional<Coin> findByUser(User user);

}
