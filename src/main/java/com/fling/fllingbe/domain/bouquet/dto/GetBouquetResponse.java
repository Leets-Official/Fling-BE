package com.fling.fllingbe.domain.bouquet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fling.fllingbe.domain.bouquet.domain.Bouquet;
import com.fling.fllingbe.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetBouquetResponse {
    private String description;
    @JsonProperty(value = "dDay")
    private String dday;
    private BouquetDesign bouquetDesign;
    private List<BouquetInfo> bouquets;
}