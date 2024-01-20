package com.fling.fllingbe.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddCardItemRequest {
    private String cardType;
    private Long price;
    private String description;
}
