package com.fling.fllingbe.domain.bouquet.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CreateBouquetRequest {
    private LocalDateTime dDay;
    private String bouquetName;
    private String wrapper;
    private String ribbon;
}
