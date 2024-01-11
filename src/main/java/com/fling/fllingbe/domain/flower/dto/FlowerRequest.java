package com.fling.fllingbe.domain.flower.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class FlowerRequest {
    private String letter;
    private String cardType;
    private String flowerType;
}
