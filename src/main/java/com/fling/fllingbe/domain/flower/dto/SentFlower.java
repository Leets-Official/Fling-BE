package com.fling.fllingbe.domain.flower.dto;


import com.fling.fllingbe.domain.flower.domain.Flower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SentFlower {
    private Long flowerId;
    private String receiver;
    private String flowerType;

    public static SentFlower fromEntity(Flower flower) {
        SentFlower sentFlower = SentFlower.builder()
                .flowerId(flower.getFlowerId())
                .receiver(flower.getReceiver().getNickname())
                .flowerType(flower.getFlowerType().getFlowerName())
                .build();
        return sentFlower;
    }
}
