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
    private String letterType;

    public static SentFlower fromEntity(Flower flower) {
        SentFlower sentFlower = SentFlower.builder()
                .flowerId(flower.getFlowerId())
                .receiver(flower.getReceiver().getNickname())
                .letterType(flower.getCardType().getCardName())
                .build();
        return sentFlower;
    }
}
