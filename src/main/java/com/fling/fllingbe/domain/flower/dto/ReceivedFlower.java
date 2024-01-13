package com.fling.fllingbe.domain.flower.dto;


import com.fling.fllingbe.domain.flower.domain.Flower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ReceivedFlower {
    private Long flowerId;
    private String sender;
    private String flowerType;

    public static ReceivedFlower fromEntity(Flower flower) {
        ReceivedFlower receivedFlower = ReceivedFlower.builder()
                .flowerId(flower.getFlowerId())
                .sender(flower.getSender().getNickname())
                .flowerType(flower.getFlowerType().getFlowerName())
                .build();
        return receivedFlower;
    }
}
