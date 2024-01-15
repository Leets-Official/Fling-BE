package com.fling.fllingbe.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetItemRequest {
    private Long id;
    public GetItemRequest(){};
}
