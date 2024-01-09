package com.fling.fllingbe.domain.item.application;


import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.dto.CardItemResponse;
import com.fling.fllingbe.domain.item.dto.FlowerItemResponse;
import com.fling.fllingbe.domain.item.repository.CardItemRepository;
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
public class CardItemService {
    final CardItemRepository cardItemRepository;
    final UserRepository userRepository;

    public List<CardItemResponse> getCardItem(UUID userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new UserNotFoundException());
        List<CardItem> cardItems = cardItemRepository.findByUser(user);

        return  cardItems.stream()
                .map(CardItemResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
