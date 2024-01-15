package com.fling.fllingbe.domain.flower.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetLetter {
    private String letter;
    private String flowerType;
    private String cardType;
}
