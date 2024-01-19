package com.fling.fllingbe.domain.store.application;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.coin.exception.InsufficientCoinBalanceException;
import com.fling.fllingbe.domain.coin.repository.CoinRepository;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.item.repository.FlowerItemRepository;
import com.fling.fllingbe.domain.item.repository.FlowerTypeRepository;
import com.fling.fllingbe.domain.store.dto.FlowerPurchaseRequest;
import com.fling.fllingbe.domain.coin.exception.CoinNotFoundException;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.flower.exception.FlowerTypeNotFoundException;
import com.fling.fllingbe.domain.flower.exception.FlowerItemNotFoundException;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final FlowerItemRepository flowerItemRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final CoinRepository coinRepository;
    private final UserRepository userRepository;

    public void purchaseFlower(FlowerPurchaseRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        FlowerType flowerType = flowerTypeRepository.findById(request.getFlowerItemId())
                .orElseThrow(FlowerTypeNotFoundException::new);
        FlowerItem flowerItem = flowerItemRepository.findByUserAndFlowerType(user, flowerType)
                .orElseThrow(FlowerItemNotFoundException::new);

        Coin userCoin = coinRepository.findByUser(user).orElseThrow(CoinNotFoundException::new);
        long totalCost = flowerType.getPrice() * request.getCount();

        if (userCoin.getCoin() < totalCost) {
            throw new InsufficientCoinBalanceException();
        }

        userCoin.setCoin((int) (userCoin.getCoin() - totalCost));
        coinRepository.save(userCoin);

        flowerItem.setCount(flowerItem.getCount() + request.getCount());
        flowerItem.setOwned(true);
        flowerItemRepository.save(flowerItem);
    }
}
