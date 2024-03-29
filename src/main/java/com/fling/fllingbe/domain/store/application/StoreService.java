package com.fling.fllingbe.domain.store.application;

import com.fling.fllingbe.domain.coin.domain.Coin;
import com.fling.fllingbe.domain.coin.exception.CoinNotFoundException;
import com.fling.fllingbe.domain.coin.exception.InsufficientCoinBalanceException;
import com.fling.fllingbe.domain.coin.repository.CoinRepository;
import com.fling.fllingbe.domain.flower.exception.FlowerItemNotFoundException;
import com.fling.fllingbe.domain.flower.exception.FlowerTypeNotFoundException;
import com.fling.fllingbe.domain.item.domain.*;
import com.fling.fllingbe.domain.item.exception.CardItemNotFoundException;
import com.fling.fllingbe.domain.item.exception.CardTypeNotFoundException;
import com.fling.fllingbe.domain.item.exception.DecoItemNotFoundException;
import com.fling.fllingbe.domain.item.exception.DecoTypeNotFoundException;
import com.fling.fllingbe.domain.item.repository.*;
import com.fling.fllingbe.domain.store.dto.CardPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.DecoPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.FlowerPurchaseRequest;
import com.fling.fllingbe.domain.store.dto.StoreResponse;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final RedisTemplate<String, String> redisTemplate;
    private final FlowerItemRepository flowerItemRepository;
    private final FlowerTypeRepository flowerTypeRepository;
    private final DecoItemRepository decoItemRepository;
    private final DecoTypeRepository decoTypeRepository;
    private final CardItemRepository cardItemRepository;
    private final CardTypeRepository cardTypeRepository;
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

    public void purchaseDeco(DecoPurchaseRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        DecoType decoType = decoTypeRepository.findById(request.getDecoId())
                .orElseThrow(DecoTypeNotFoundException::new);
        DecoItem decoItem = decoItemRepository.findByUserAndDecoType(user, decoType)
                .orElseThrow(DecoItemNotFoundException::new);

        Coin userCoin = coinRepository.findByUser(user).orElseThrow(CoinNotFoundException::new);

        if (userCoin.getCoin() < decoType.getPrice()) {
            throw new InsufficientCoinBalanceException();
        }

        userCoin.setCoin((int) (userCoin.getCoin() - decoType.getPrice()));
        coinRepository.save(userCoin);

        decoItem.setOwned(true);
        decoItemRepository.save(decoItem);
    }

    public void purchaseCard(CardPurchaseRequest request, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        CardType cardType = cardTypeRepository.findById(request.getCardId())
                .orElseThrow(CardTypeNotFoundException::new);
        CardItem cardItem = cardItemRepository.findByUserAndCardType(user, cardType)
                .orElseThrow(CardItemNotFoundException::new);

        Coin userCoin = coinRepository.findByUser(user).orElseThrow(CoinNotFoundException::new);
        long totalCost = cardType.getPrice() * request.getCount();

        if (userCoin.getCoin() < totalCost) {
            throw new InsufficientCoinBalanceException();
        }

        userCoin.setCoin((int) (userCoin.getCoin() - totalCost));
        coinRepository.save(userCoin);

        cardItem.setCount(cardItem.getCount() + request.getCount());
        cardItem.setOwned(true);
        cardItemRepository.save(cardItem);
    }
    public StoreResponse getStoreItems(String email) {
        List<DecoType> decoTypes = new ArrayList<>();
        for(int number = 0 ; number < 3 ; number++ ){
            String randomDecoTypeId = redisTemplate.opsForValue().get(email+"_"+number);
            DecoType decoType;
            if(randomDecoTypeId == null){
                decoType = decoTypeRepository.findById(1L).get();
            }else{
                decoType = decoTypeRepository.findById(Long.parseLong(randomDecoTypeId)).get();
            }
            decoTypes.add(decoType);
        }
        List<StoreResponse.DecoItemDTO> decoItems = decoTypes.stream()
                .map(deco -> new StoreResponse.DecoItemDTO(deco.getDecoTypeId(), deco.getDecoTypeName(), deco.getPrice()))
                .sorted(Comparator.comparing(StoreResponse.DecoItemDTO::getItemId))
                .collect(Collectors.toList());

        List<FlowerType> flowerTypes = flowerTypeRepository.findAll();
        List<StoreResponse.FlowerItemDTO> flowerItems = flowerTypeRepository.findAll().stream()
                .filter(flower -> flower.getPrice() > 0)
                .map(flower -> new StoreResponse.FlowerItemDTO(flower.getFlowerTypeId(), flower.getFlowerName(), flower.getPrice()))
                .sorted(Comparator.comparing(StoreResponse.FlowerItemDTO::getItemId))
                .collect(Collectors.toList());

        List<CardType> cardTypes = cardTypeRepository.findAll();
        List<StoreResponse.LetterItemDTO> letterItems = cardTypeRepository.findAll().stream()
                .filter(card -> card.getPrice() > 0)
                .map(card -> new StoreResponse.LetterItemDTO(card.getCardTypeId(), card.getCardName(), card.getPrice()))
                .sorted(Comparator.comparing(StoreResponse.LetterItemDTO::getItemId))
                .collect(Collectors.toList());

        return new StoreResponse(decoItems, flowerItems, letterItems);
    }
}