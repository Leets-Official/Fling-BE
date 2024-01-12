package com.fling.fllingbe.domain.bouquet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateBouquetRequest {
    String wrapper;
    String ribbon;
}
