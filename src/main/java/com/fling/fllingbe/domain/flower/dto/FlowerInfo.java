package com.fling.fllingbe.domain.flower.dto;


import com.fling.fllingbe.domain.flower.domain.Flower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FlowerInfo {
    private Long flowerId;
    private String sender;
    private String flowerType;

    public static FlowerInfo fromEntity(Flower flower) {
        FlowerInfo flowerInfo = FlowerInfo.builder()
                .flowerId(flower.getFlowerId())
                .sender(flower.getSender().getNickname())
                .flowerType(flower.getFlowerType().getFlowerName())
                .build();
        return flowerInfo;
    }
}
