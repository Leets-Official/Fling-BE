package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import com.fling.fllingbe.domain.item.dto.*;
import com.fling.fllingbe.domain.item.repository.CardItemRepository;
import com.fling.fllingbe.domain.item.repository.CardTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class
CardItemService {
    final CardItemRepository cardItemRepository;
    final CardTypeRepository cardTypeRepository;
    final UserRepository userRepository;

    public List<CardItemResponse> getCardItem(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException());
        List<CardItem> cardItems = cardItemRepository.findByUser(user);

        return cardItems.stream()
                .map(CardItemResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public CardType minusCardItem(FlowerRequest request, User user) {
        CardType cardType = cardTypeRepository.findByCardName(request.getCardType()).get();
        CardItem cardItem = cardItemRepository.findByUserAndCardType(user, cardType).get();
        if (cardType.getPrice() != 0) {
            CardItem newCardItem = new CardItem().builder()
                    .cardItemId(cardItem.getCardItemId())
                    .user(user)
                    .cardType(cardType)
                    .count(cardItem.getCount() - 1)
                    .owned(true)
                    .build();
            cardItemRepository.save(newCardItem);
        } else {
            CardItem newCardItem = new CardItem().builder()
                    .cardItemId(cardItem.getCardItemId())
                    .user(user)
                    .cardType(cardType)
                    .count(cardItem.getCount())
                    .owned(true)
                    .build();
            cardItemRepository.save(newCardItem);
        }
        return cardType;
    }

    public void createDefaultCardItem(User user) {
        List<CardType> cardTypeList = cardTypeRepository.findAll();
        for (CardType cardType : cardTypeList) {
            Long count = (cardType.getPrice() == 0) ? -1L : 0L;
            CardItem cardItem = CardItem.builder()
                    .cardType(cardType)
                    .user(user)
                    .owned(cardType.getPrice() == 0)
                    .count(count)
                    .build();
            cardItemRepository.save(cardItem);
        }
    }

    public GetItemResponse getCardItemInfo(Long id) {
        CardType cardType = cardTypeRepository.findById(id).get();
        GetItemResponse getItemResponse = GetItemResponse.builder()
                .itemName(cardType.getCardName())
                .description(cardType.getDescription())
                .build();
        return getItemResponse;
    }
    public String addNewCardItem(AddCardItemRequest request) {
        CardType cardType = CardType.builder()
                .cardName(request.getCardType())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        cardTypeRepository.save(cardType);
        List<User> userList = userRepository.findAll();
        CardType newCardType = cardTypeRepository.findByCardName(request.getCardType()).get();
        Long count = (newCardType.getPrice() == 0)? -1L : 0L;
        for (User user : userList) {
            CardItem cardItem = CardItem.builder()
                    .user(user)
                    .cardType(newCardType)
                    .count(count)
                    .owned(newCardType.getPrice() == 0)
                    .build();
            cardItemRepository.save(cardItem);
        }
        return "아이템 추가가 완료되었습니다.";
    }
}
