package com.fling.fllingbe.domain.item.dto;

import com.fling.fllingbe.domain.item.domain.FlowerItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class FlowerItemResponse {
    private String flowerType;
    private Long count;

    public static FlowerItemResponse fromEntity(FlowerItem flowerItem) {
        return new FlowerItemResponse(flowerItem.getFlowerType().getFlowerName(), flowerItem.getCount());
    }
}
