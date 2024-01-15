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
    private boolean owned;
    private Long amount;
    public static CardItemInfo fromEntity(CardItem cardItem) {
        CardItemInfo cardItemInfo = CardItemInfo.builder()
                .id(cardItem.getCardType().getCardTypeId())
                .type(cardItem.getCardType().getCardName())
                .owned(cardItem.isOwned())
                .amount(cardItem.getCount())
                .build();
        return cardItemInfo;
    }
}
