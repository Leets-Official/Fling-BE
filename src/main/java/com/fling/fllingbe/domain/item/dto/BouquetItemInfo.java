package com.fling.fllingbe.domain.item.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BouquetItemInfo {
    private Long id;
    private String type;
}
