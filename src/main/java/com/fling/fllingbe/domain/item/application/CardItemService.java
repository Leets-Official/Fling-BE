package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.flower.dto.FlowerRequest;
import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.CardType;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.dto.CardItemResponse;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.dto.GetItemRequest;
import com.fling.fllingbe.domain.item.dto.GetItemResponse;
import com.fling.fllingbe.domain.item.repository.CardItemRepository;
import com.fling.fllingbe.domain.item.repository.CardTypeRepository;
import com.fling.fllingbe.domain.user.domain.User;
import com.fling.fllingbe.domain.user.exception.UserNotFoundException;
import com.fling.fllingbe.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class
CardItemService {
    final CardItemRepository cardItemRepository;
    final CardTypeRepository cardTypeRepository;
    final UserRepository userRepository;

    public List<CardItemResponse> getCardItem(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(()->new UserNotFoundException());
        List<CardItem> cardItems = cardItemRepository.findByUser(user);

        return  cardItems.stream()
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
                    .build();
            cardItemRepository.save(newCardItem);
        } else {
            CardItem newCardItem = new CardItem().builder()
                    .cardItemId(cardItem.getCardItemId())
                    .user(user)
                    .cardType(cardType)
                    .count(cardItem.getCount())
                    .build();
            cardItemRepository.save(newCardItem);
        }
        return cardType;
    }
    public void createDefaultCardItem(User user) {
        List<CardType> cardTypeList = cardTypeRepository.findAll();
        for (CardType cardType : cardTypeList) {
            CardItem cardItem = CardItem.builder()
                    .cardType(cardType)
                    .user(user)
                    .owned(cardType.getPrice() == 0)
                    .count(0L)
                    .build();
            cardItemRepository.save(cardItem);
        }
    }

    public GetItemResponse getCardItemInfo(GetItemRequest getItemRequest) {
        CardType cardType = cardTypeRepository.findById(getItemRequest.getId()).get();
        GetItemResponse getItemResponse = GetItemResponse.builder()
                .itemName(cardType.getCardName())
                .description(cardType.getDescription())
                .build();
        return getItemResponse;
    }
}
