package com.fling.fllingbe.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardPurchaseRequest {
    private Long cardId;
    private Integer count;
}