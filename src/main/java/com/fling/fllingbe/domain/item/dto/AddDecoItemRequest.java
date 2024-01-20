package com.fling.fllingbe.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AddDecoItemRequest {
    private String decoType;
    private Long price;
    private String description;
}
