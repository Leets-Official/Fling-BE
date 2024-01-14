package com.fling.fllingbe.domain.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowerPurchaseRequest {
    private Long flowerItemId;
    private Long count;
}