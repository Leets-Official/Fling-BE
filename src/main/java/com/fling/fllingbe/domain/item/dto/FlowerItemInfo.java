package com.fling.fllingbe.domain.item.dto;


import com.fling.fllingbe.domain.item.domain.FlowerItem;
import com.fling.fllingbe.domain.item.domain.FlowerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FlowerItemInfo {
    private Long id;
    private String type;
    private boolean owned;
    private Long amount;
    public static FlowerItemInfo fromEntity(FlowerItem flowerItem) {
        FlowerItemInfo flowerItemInfo = FlowerItemInfo.builder()
                .id(flowerItem.getFlowerType().getFlowerTypeId())
                .type(flowerItem.getFlowerType().getFlowerName())
                .owned(flowerItem.isOwned())
                .amount(flowerItem.getCount())
                .build();
        return flowerItemInfo;
    }
}
