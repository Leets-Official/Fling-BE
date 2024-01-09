package com.fling.fllingbe.domain.item.dto;

import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.FlowerItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardItemResponse {
    private String CardType;
    private Long count;

    public static CardItemResponse fromEntity(CardItem cardItem) {
        return new CardItemResponse(cardItem.getCardType().getCardName(), cardItem.getCount());
    }
}
