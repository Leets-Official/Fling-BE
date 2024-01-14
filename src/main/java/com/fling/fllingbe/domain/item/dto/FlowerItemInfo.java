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

    public static FlowerItemInfo fromEntity(FlowerType flowerType) {
        FlowerItemInfo flowerItemInfo = FlowerItemInfo.builder()
                .id(flowerType.getFlowerTypeId())
                .type(flowerType.getFlowerName())
                .build();
        return flowerItemInfo;
    }
}
