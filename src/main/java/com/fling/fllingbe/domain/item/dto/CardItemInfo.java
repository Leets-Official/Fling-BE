package com.fling.fllingbe.domain.item.dto;


import com.fling.fllingbe.domain.item.domain.CardItem;
import com.fling.fllingbe.domain.item.domain.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CardItemInfo {
    private Long id;
    private String type;

    public static CardItemInfo fromEntity(CardType cardType) {
        CardItemInfo cardItemInfo = CardItemInfo.builder()
                .id(cardType.getCardTypeId())
                .type(cardType.getCardName())
                .build();
        return cardItemInfo;
    }
}
