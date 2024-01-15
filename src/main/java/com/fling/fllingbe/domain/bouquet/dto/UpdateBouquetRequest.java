package com.fling.fllingbe.domain.bouquet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdateBouquetRequest {
    private String wrapper;
    private String ribbon;
    private String item1;
    private String item2;
    private String item3;
}
